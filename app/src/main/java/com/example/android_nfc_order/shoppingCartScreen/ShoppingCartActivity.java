package com.example.android_nfc_order.shoppingCartScreen;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_nfc_order.R;
import com.example.android_nfc_order.data.ShopItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

interface AddButtonClickedCallback {
  void addItemClicked(ShopItem item);
}

interface RemoveButtonClickedCallback {
  void removeItemClicked(ShopItem item);
}

public class ShoppingCartActivity
    extends AppCompatActivity implements ShoppingCartContract.View {

  public static String TAG = ShoppingCartActivity.class.getSimpleName();

  private ShoppingCartContract.Presenter presenter;

  private RecyclerView recyclerView;
  private ShoppingCartListAdapter listAdapter;

  private Toolbar toolbar;
  private FloatingActionButton doneButton;
  private ImageButton backButton;
  private TextView toolbar_title, total_price_label;

  public static final String MIME_TEXT_PLAIN = "text/plain";
  private NfcAdapter nfcAdapter;
  private AlertDialog nfcAlert;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shopping_cart);

    total_price_label = findViewById(R.id.shop_total_price);

    listAdapter = new ShoppingCartListAdapter(
        new AddButtonClickedCallback() {
          @Override
          public void addItemClicked(ShopItem item) {
            presenter.addItemClicked(item);
          }
        },
        new RemoveButtonClickedCallback() {
          @Override
          public void removeItemClicked(ShopItem item) {
            presenter.removeItemClicked(item);
          }
        });

    recyclerView = findViewById(R.id.shoppingcart_recycler);
    recyclerView.setAdapter(listAdapter);

    toolbar = findViewById(R.id.toolbar);

    toolbar_title = findViewById(R.id.toolbar_title);
    toolbar_title.setText(R.string.shopping_cart_title);

    backButton = findViewById(R.id.toolbar_backButton);
    backButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });

    setSupportActionBar(toolbar);

    doneButton = findViewById(R.id.floatingDoneButton);
    doneButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        presenter.onDoneClicked();
      }
    });

    // do the setup
    ShoppingCartScreen.configure(this);

  }

  @Override
  protected void onResume() {
    super.onResume();

    // do some work
    presenter.fetchData();
  }

  @Override
  public void injectPresenter(ShoppingCartContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void displayData(final ShoppingCartViewModel viewModel) {
    Log.e(TAG, "__________________ DISPLAY DATA ________________");
    final String totalPrice = "Total: $" + viewModel.totalPrice;

    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        listAdapter.setItems(viewModel.shopItemList);
        total_price_label.setText(totalPrice);
      }
    });
  }

  @Override
  public void onBackPressed() {
    this.finish();
  }

  @Override
  public void successfulSubmitAlert(boolean success, String restaurant_name) {
    String title = "";
    String message = "";
    if (success) {
      title = "Successful operation.";
      message = "Your order has been successfully submitted to: " + restaurant_name;
    } else {
      title = "Error in operation.";
      message = "There has been an error. Please try again.";
    }

    this.nfcAlert = new AlertDialog.Builder(this).create();
    this.nfcAlert.setTitle(title);
    this.nfcAlert.setMessage(message);
    this.nfcAlert.setButton(AlertDialog.BUTTON_NEGATIVE, "OKAY",
        new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            nfcAlert.dismiss();
          }
        });
    this.nfcAlert.show();
  }

  @Override
  public void presentToast(String text) {
    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void setUpNFC() {
    nfcAdapter = NfcAdapter.getDefaultAdapter(this);

    if (nfcAdapter == null) {
      // Stop here, we definitely need NFC
      Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
      return;
    }

    if (!nfcAdapter.isEnabled()) {
      this.nfcAlert = new AlertDialog.Builder(this).create();
      this.nfcAlert.setTitle("NFC disabled.");
      this.nfcAlert.setMessage("Please enable NFC first.");
      this.nfcAlert.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
          new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
              nfcAlert.dismiss();
            }
          });

      this.nfcAlert.show();
    } else {
      setupForegroundDispatch(this, nfcAdapter);

      this.nfcAlert = new AlertDialog.Builder(this).create();
      this.nfcAlert.setTitle("NFC ready.");
      this.nfcAlert.setMessage("Please approach your phone to the NFC tag.");
      this.nfcAlert.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
          new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
              nfcAlert.cancel();
            }
          });
      this.nfcAlert.setOnCancelListener(new DialogInterface.OnCancelListener() {
        @Override
        public void onCancel(DialogInterface dialog) {
          Toast.makeText(ShoppingCartActivity.this, "NFC cancelled.", Toast.LENGTH_LONG).show();
          stopForegroundDispatch(ShoppingCartActivity.this, nfcAdapter);
        }
      });

      this.nfcAlert.show();
    }
  }

  private void handleIntent(Intent intent) {
    String action = intent.getAction();
    if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {

      String type = intent.getType();
      if (MIME_TEXT_PLAIN.equals(type)) {

        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        new NdefReaderTask().execute(tag);

      } else {
        Log.d(TAG, "Wrong mime type: " + type);
      }
    }
  }

  public static void setupForegroundDispatch(final Activity activity, NfcAdapter adapter) {
    final Intent intent = new Intent(activity.getApplicationContext(), activity.getClass());
    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

    final PendingIntent pendingIntent = PendingIntent.getActivity(activity.getApplicationContext(), 0, intent, 0);

    IntentFilter[] filters = new IntentFilter[1];
    String[][] techList = new String[][]{};

    // Notice that this is the same filter as in our manifest.
    filters[0] = new IntentFilter();
    filters[0].addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
    filters[0].addCategory(Intent.CATEGORY_DEFAULT);
    try {
      filters[0].addDataType(MIME_TEXT_PLAIN);
    } catch (IntentFilter.MalformedMimeTypeException e) {
      throw new RuntimeException("Check your mime type.");
    }

    adapter.enableForegroundDispatch(activity, pendingIntent, filters, techList);
  }

  public static void stopForegroundDispatch(final Activity activity, NfcAdapter adapter) {
    adapter.disableForegroundDispatch(activity);
  }

  @Override
  protected void onNewIntent(Intent intent) {
    handleIntent(intent);
  }

  private class NdefReaderTask extends AsyncTask<Tag, Void, String> {

    @Override
    protected String doInBackground(Tag... params) {
      Tag tag = params[0];

      Ndef ndef = Ndef.get(tag);
      if (ndef == null) {
        // NDEF is not supported by this Tag.
        return null;
      }

      NdefMessage ndefMessage = ndef.getCachedNdefMessage();
      NdefRecord[] records = ndefMessage.getRecords();
      String result_3 = "";

      // Read register 3
      NdefRecord ndefRecord = records[3];
      if (ndefRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
        try {
          result_3 = readText(ndefRecord);
        } catch (UnsupportedEncodingException e) {
          Log.e(TAG, "Unsupported Encoding", e);
        }
      }

      // Read register 4
      String result_4 = "";
      ndefRecord = records[4];
      if (ndefRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
        try {
          result_4 = readText(ndefRecord);
        } catch (UnsupportedEncodingException e) {
          Log.e(TAG, "Unsupported Encoding", e);
        }
      }

      return result_3 + "&" + result_4;
    }

    private String readText(NdefRecord record) throws UnsupportedEncodingException {

      byte[] payload = record.getPayload();

      // Get the Text Encoding
      String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";

      // Get the Language Code
      int languageCodeLength = payload[0] & 0063;

      // String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");
      // e.g. "en"

      // Get the Text
      return new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
    }

    @Override
    protected void onPostExecute(String result) {
      if (result != null) {

        int separator = result.indexOf('&');
        String restaurant_name = result.substring(0, separator);
        String doc_ref = result.substring(separator + 1);
        String[] results = {restaurant_name, doc_ref};

        nfcAlert.dismiss();
        presenter.onNFCRead(results);
        stopForegroundDispatch(ShoppingCartActivity.this, nfcAdapter);
      }
    }
  }


}

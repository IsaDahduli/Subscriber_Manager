package com.isa.subscriber_manager

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.isa.subscriber_manager.Api
import com.isa.subscriber_manager.RetrofitClient
import com.isa.subscriber_manager.ResponseModel

class Admin_Update_Subscriber_Activity : AppCompatActivity() {

    private lateinit var editTextMsisdn: TextInputEditText
    private lateinit var editTextIccid: TextInputEditText
    private lateinit var spinnerChargingType: Spinner
    private lateinit var editTextChargingType: TextInputEditText
    private lateinit var spinnerSimProfileId: Spinner
    private lateinit var editTextSimProfileId: TextInputEditText
    private lateinit var spinnerServiceType: Spinner
    private lateinit var editTextServiceType: TextInputEditText
    private lateinit var editTextMvno: TextInputEditText
    private lateinit var editTextTariffCode: TextInputEditText
    private lateinit var editTextTac: TextInputEditText
    private lateinit var editTextBrand: TextInputEditText
    private lateinit var editTextModel: TextInputEditText
    private lateinit var buttonUpdateSubscriber: Button

    private var id: Int = -1 // To hold the subscriber's ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_update_subscriber)
        initViews()
        retrieveDataFromIntent()  // Get subscriber ID from Intent
        setupListeners()
    }



    private fun initViews() {
        editTextMsisdn = findViewById(R.id.edit_text_msisdn)
        editTextIccid = findViewById(R.id.edit_text_iccid)
        spinnerChargingType = findViewById(R.id.spinner_chargingtype)
        editTextChargingType = findViewById(R.id.edit_text_chargingtype)
        spinnerSimProfileId = findViewById(R.id.spinner_ProfileID)
        editTextSimProfileId = findViewById(R.id.edit_text_profileID)
        spinnerServiceType = findViewById(R.id.spinner_servicetype)
        editTextServiceType = findViewById(R.id.edit_text_servicetype)
        editTextMvno = findViewById(R.id.edit_text_mvno)
        editTextTariffCode = findViewById(R.id.edit_text_tarif_code)
        editTextTac = findViewById(R.id.edit_text_tac)
        editTextBrand = findViewById(R.id.edit_text_brand)
        editTextModel = findViewById(R.id.edit_text_model)
        buttonUpdateSubscriber = findViewById(R.id.btn_update_subscriber)

        val dummyChargingTypes = arrayOf("Postpaid", "Prepaid")
        val dummyProfileIds = arrayOf("Profile1", "Profile2", "Profile3")
        val dummyServiceTypes = arrayOf("Service1", "Service2", "Service3")

        spinnerChargingType.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dummyChargingTypes)
        spinnerSimProfileId.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dummyProfileIds)
        spinnerServiceType.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dummyServiceTypes)
    }

    private fun retrieveDataFromIntent() {

        // Retrieve other data
        editTextMsisdn.setText(intent.getStringExtra("msisdn"))
        editTextIccid.setText(intent.getStringExtra("iccid"))
        editTextChargingType.setText(intent.getStringExtra("chargingType"))
        editTextSimProfileId.setText(intent.getStringExtra("simProfileId"))
        editTextServiceType.setText(intent.getStringExtra("serviceType"))
        editTextMvno.setText(intent.getStringExtra("mvnoName"))
        editTextTariffCode.setText(intent.getStringExtra("tariffCode"))
        editTextTac.setText(intent.getStringExtra("tac"))
        editTextBrand.setText(intent.getStringExtra("brand"))
        editTextModel.setText(intent.getStringExtra("model"))
    }

    private fun setupListeners() {
        buttonUpdateSubscriber.setOnClickListener {
            val msisdn = editTextMsisdn.text.toString().trim()
            val iccid = editTextIccid.text.toString().trim()
            val chargingType = editTextChargingType.text.toString().trim()
            val simProfileId = editTextSimProfileId.text.toString().trim()
            val serviceType = editTextServiceType.text.toString().trim()
            val mvnoName = editTextMvno.text.toString().trim()
            val tariffCode = editTextTariffCode.text.toString().trim()
            val tac = editTextTac.text.toString().trim()
            val brand = editTextBrand.text.toString().trim()
            val model = editTextModel.text.toString().trim()

            updateSubscriber(msisdn, iccid, chargingType, simProfileId, serviceType, mvnoName, tariffCode, tac, brand, model)
        }

        spinnerChargingType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                editTextChargingType.setText(parent.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        spinnerSimProfileId.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                editTextSimProfileId.setText(parent.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        spinnerServiceType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                editTextServiceType.setText(parent.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun updateSubscriber(
        msisdn: String,
        iccid: String,
        chargingType: String,
        simProfileId: String,
        serviceType: String,
        mvnoName: String,
        tariffCode: String,
        tac: String,
        brand: String,
        model: String
    ) {
        if (msisdn.isEmpty() || iccid.isEmpty() || chargingType.isEmpty() || simProfileId.isEmpty() || serviceType.isEmpty() || mvnoName.isEmpty() || tariffCode.isEmpty() || tac.isEmpty() || brand.isEmpty() || model.isEmpty() ) 
        {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
            return
        }
        
        val api = RetrofitClient.api  // Use the static api property
        val call = api.updateSubscriber(
            msisdn,
            iccid,
            chargingType,
            simProfileId,
            serviceType,
            mvnoName,
            tariffCode,
            tac,
            brand,
            model
        )

        call!!.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.isSuccessful) {
                    val responseModel = response.body()
                    if (responseModel?.status == "success") {
                        Toast.makeText(this@Admin_Update_Subscriber_Activity, "Subscriber updated successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@Admin_Update_Subscriber_Activity, responseModel?.message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@Admin_Update_Subscriber_Activity, "Failed to update subscriber", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(this@Admin_Update_Subscriber_Activity, "Error: " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}

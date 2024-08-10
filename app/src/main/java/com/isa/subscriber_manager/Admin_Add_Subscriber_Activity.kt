package com.isa.subscriber_manager

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Admin_Add_Subscriber_Activity : AppCompatActivity() {

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
    private lateinit var buttonAddSubscriber: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_add_subscriber)

        initViews()
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
        buttonAddSubscriber = findViewById(R.id.btn_add_update_subscriber)


        val dummyChargingTypes = arrayOf("Postpaid","Prepaid")
        val dummyProfileIds = arrayOf("Profile1", "Profile2", "Profile3")
        val dummyServiceTypes = arrayOf("Service1", "Service2", "Service3")

        spinnerChargingType.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dummyChargingTypes)
        spinnerSimProfileId.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dummyProfileIds)
        spinnerServiceType.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dummyServiceTypes)
    }

    private fun setupListeners() {
        buttonAddSubscriber.setOnClickListener {
            handleAddSubscriber()
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

    private fun handleAddSubscriber() {
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

        if (TextUtils.isEmpty(msisdn) || TextUtils.isEmpty(iccid) || TextUtils.isEmpty(chargingType) ||
            TextUtils.isEmpty(simProfileId) || TextUtils.isEmpty(serviceType) ||
            TextUtils.isEmpty(mvnoName) || TextUtils.isEmpty(tariffCode) ||
            TextUtils.isEmpty(tac) || TextUtils.isEmpty(brand) || TextUtils.isEmpty(model)) {

            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Call the API to add the subscriber
        val call = RetrofitClient.api.addSubscriber(msisdn, iccid, chargingType, simProfileId, serviceType, mvnoName, tariffCode, tac, brand, model)

        call?.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.isSuccessful && response.body()?.status == "success") {
                    Toast.makeText(this@Admin_Add_Subscriber_Activity, "Subscriber added successfully", Toast.LENGTH_SHORT).show()
                    val i = Intent(
                        this@Admin_Add_Subscriber_Activity,MainAdminMenuActivity::class.java)
                    startActivity(i)
                } else {
                    Toast.makeText(this@Admin_Add_Subscriber_Activity, "Failed to add subscriber", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(this@Admin_Add_Subscriber_Activity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }
}

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.isa.subscriber_manager.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class SubscriberAdapter(context: Context, private val subscribers: MutableList<Subscriber?>) : ArrayAdapter<Subscriber>(context, 0, subscribers) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val subscriber = getItem(position)

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.subscriber_list_item, parent, false)
        }

        val msisdnTextView = view!!.findViewById<TextView>(R.id.msisdn_item)
        val iccidTextView = view.findViewById<TextView>(R.id.iccid_item)
        val removeButton = view.findViewById<Button>(R.id.remove_subscriber)
        val editButton = view.findViewById<Button>(R.id.edit_subscriber)

        msisdnTextView.text = subscriber!!.msisdn
        iccidTextView.text = subscriber.iccid

        removeButton.setOnClickListener {
            deleteSubscriber(subscriber, position)
        }

        editButton.setOnClickListener {
            // Pass the subscriber data to the edit activity
            val intent = Intent(view.context, Admin_Update_Subscriber_Activity::class.java)
            intent.putExtra("msisdn", subscriber.msisdn)
            intent.putExtra("iccid", subscriber.iccid)
            intent.putExtra("chargingType", subscriber.chargingType)
            intent.putExtra("simProfileId", subscriber.simProfileId)
            intent.putExtra("serviceType", subscriber.serviceType)
            intent.putExtra("mvnoName", subscriber.mvnoName)
            intent.putExtra("tariffCode", subscriber.tariffCode)
            intent.putExtra("tac", subscriber.tac)
            intent.putExtra("brand", subscriber.brand)
            intent.putExtra("model", subscriber.model)

            view.context.startActivity(intent)
        }

        return view
    }


    private fun deleteSubscriber(subscriber: Subscriber, position: Int) {
        val api = RetrofitClient.api
        val call = api.deleteSubscriber(subscriber.msisdn)

        call.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody?.status == "success") {
                        subscribers.removeAt(position)
                        notifyDataSetChanged()
                        Toast.makeText(context, "Subscriber removed successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Failed to remove subscriber: ${responseBody?.message}", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Toast.makeText(context, "Failed to remove subscriber: $errorBody", Toast.LENGTH_SHORT).show()
                    println("Error Body: $errorBody")
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(context, "Error: " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}

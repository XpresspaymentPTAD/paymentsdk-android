package com.xpresspayments.paymentsdkandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout
import com.xpresspayments.paymentsdk.api.OnTransactionListener
import com.xpresspayments.paymentsdk.api.Xpay
import com.xpresspayments.paymentsdk.api.model.InitialisePayment
import com.xpresspayments.paymentsdk.entity.Transaction
import java.util.*

class StartingActivity : AppCompatActivity() {

    var amountLayout: TextInputLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starting)

        amountLayout = findViewById(R.id.edl_amount)

        findViewById<Button>(R.id.btn_continue).setOnClickListener {

            val amount = amountLayout?.editText?.text?.trim().toString()
            if (amount.isEmpty()) {
                amountLayout?.error = "Amount can not be empty"
            } else {
                amountLayout?.error = null
                pay(amount.toDouble())
            }
        }

    }

    fun pay(amount: Double){

        val date = Date()
        val transactionId =
            Math.floor(Math.random() * 1000000).toInt()
        val initialisePayment = InitialisePayment(
            (date.time + transactionId).toString(),
            amount,
            "yourvalidemail@mail.com",
            "yourpublickey",
            false
        )

        Xpay(this).transact(initialisePayment, object :
            OnTransactionListener {
            override fun onSuccess(transaction: Transaction?) {
                Log.d("MainActivity", "result: $transaction")
            }

            override fun onError(throwable: Throwable, transaction: Transaction?) {
                Log.d("MainActivity", "error: $throwable")
            }
        })
    }
}
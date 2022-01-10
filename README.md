## Xpresspayments Android

This is a library for easy integration of [Xpresspayments](https://https://www.xpresspayments.com.com) payment gateway with your Android application.

## Get Started

###### Step 1

Add the JitPack repository to your root build.gradle at the end of repositories.

```
allprojects {
    repositories {
         maven { url "https://jitpack.io" }
     }
}
```

###### Step 2

Add the dependency to your app build.gradle and build.

```
 dependencies {
     implementation 'com.github.XpresspaymentPTAD:payment-gateway-android:1.0.6'
 }

```

## Sample Function Request

To start the transaction, you'll need to pass information such as transactionId, amount, email, publicKey, etc. Public key, Email and amount are required and must be valid.

This repository contain a kotlin sample. here is a java sample:

```
private void pay() {
        Date date =  new Date();
        int transactionId = (int) Math.floor(Math.random() * 1000000);
        InitialisePayment initialisePayment = new InitialisePayment(
                String.valueOf(date.getTime() + transactionId),
                1000,
                "yourvalidemail@mail.com",
                "yourpublickey",
                false
                );

        new Xpay(this).transact(initialisePayment, new OnTransactionListener() {
            @Override
            public void onSuccess(Transaction transaction) {
                Log.d("MainActivity", "result " + transaction);
            }

            @Override
            public void onError(Throwable throwable, Transaction transaction) {
                Log.d("MainActivity", "error " + throwable.getMessage());
            }
        });
    }

```

## Test

We provide test card for your use instead of using your own debit/credit cards.

```
5399 8300 0000 0008
MM/YY: 05/30
CVV: 000
PIN: 1234
TOKEN: 123456
```

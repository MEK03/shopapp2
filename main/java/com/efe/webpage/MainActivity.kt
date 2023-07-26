package com.efe.webpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import com.shopify.buy3.*
import com.shopify.buy3.Storefront.*
import com.shopify.buy3.GraphCall
import com.shopify.buy3.GraphResponse

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

    }

    fun goToCategoryPage() {
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }

    private fun executeGraphQuery() {
        val client: GraphClient = GraphClient.build(
            this,
            "xennio.myshopify.com",
            "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"
        )

        val query = query { rootQuery: QueryRootQuery ->
            rootQuery
                .shop { shopQuery: ShopQuery ->
                    shopQuery
                        .name()
                }
        }

        val call: QueryGraphCall = client.queryGraph(query)
        call.enqueue(object : GraphCall.Callback<Storefront.QueryRoot> {
            override fun onResponse(response: GraphResponse<Storefront.QueryRoot>) {
                val name = response.data?.shop?.name
                // Handle the response here
            }

            override fun onFailure(error: GraphError) {
                Log.e("InUse", "Failed to execute query", error)
                // Handle the failure here
            }
        })
    }
}
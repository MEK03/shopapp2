package com.efe.webpage

import com.apollographql.apollo.api.Input
import com.shopify.buy3.Storefront

object ShopifyApiHelper {
    fun retrieve (): {
        QueryRootQuery query = Storefront.query(rootQueryBuilder ->
        rootQueryBuilder
            .shop(shopQueryBuilder ->
        shopQueryBuilder
            .name()
        )
        )
    }
    fun getFirstThreeProductsQuery(): Storefront.QueryRootQuery {
        return Storefront.query { rootQuery ->
            rootQuery.products(args = Input.fromNullable(Storefront.ProductConnectionFirstArgs(3))) { productConnectionQuery ->
                productConnectionQuery.edges { productEdgeQuery ->
                    productEdgeQuery.node { productQuery ->
                        productQuery.title()
                        productQuery.id()
                    }
                }
            }
        }
    }
}

package com.glosebooks.store.picasso

import android.support.annotation.DrawableRes
import android.view.View
import android.widget.ImageView

import com.squareup.picasso.Picasso

import trikita.anvil.Anvil

import trikita.anvil.BaseDSL.attr

/**
 * Created by Julien on 09/01/2018.
 */
object PicassoLoadDSL {

    /** Anvil attribute setter from Request
     * @param request : the request returned from load
     */
    fun picasso(request: Request) {
        attr(PicassoImageUrlFunc.instance, request)
    }

    /** Load image from link. Set drawable during loading
     * @param url : the url of the image to load
     * @param placeholder : the drawable to show*/
    fun load(url: String, @DrawableRes placeholder: Int): Request {
        return Request(url, placeholder)
    }

    /** Class for Picasso loading*/
    private class PicassoImageUrlFunc : Anvil.AttrFunc<Request> {
        /** Function called by Anvil to set image
         * @param v : the view to update
         * @param arg : the request
         * @param old : previous request
         */
        override fun apply(v: View, arg: Request, old: Request?) {
            if (v is ImageView) {
                Picasso.with(v.getContext())
                        .load(arg.url)
                        .placeholder(arg.placeholder)
                        .into(v)
            }
        }

        companion object {
            internal val instance = PicassoImageUrlFunc()
        }
    }

    class Request internal constructor(internal val url: String, internal val placeholder: Int)
}
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

    fun picasso(request: Request) {
        attr(PicassoImageUrlFunc.instance, request)
    }

    fun load(url: String, @DrawableRes placeholder: Int): Request {
        return Request(url, placeholder)
    }

    private class PicassoImageUrlFunc : Anvil.AttrFunc<Request> {
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
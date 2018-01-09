package com.glosebooks.view

import android.text.TextUtils
import android.widget.ImageView
import android.widget.LinearLayout
import com.brianegan.bansa.Store
import com.glosebooks.R
import com.glosebooks.store.ApplicationState
import com.glosebooks.dispatcher.BOOK_CLICKED
import com.glosebooks.store.entities.Book
import com.glosebooks.store.picasso.PicassoLoadDSL
import trikita.anvil.DSL
import trikita.anvil.cardview.v7.CardViewv7DSL

/**
 * Created by Julien on 09/01/2018.
 */
fun bookView(model: BookViewModel) {
    DSL.linearLayout {
        DSL.size(DSL.FILL, DSL.WRAP)
        DSL.orientation(LinearLayout.VERTICAL)
        DSL.margin(0, DSL.dip(10))
        DSL.onClick {
            model.clickHandler()
        }

        DSL.linearLayout {
            DSL.size(300, DSL.WRAP)
            DSL.orientation(LinearLayout.VERTICAL)
            DSL.gravity(DSL.CENTER_HORIZONTAL)
            DSL.layoutGravity(DSL.CENTER_HORIZONTAL)

            CardViewv7DSL.cardView {
                DSL.size(DSL.FILL, DSL.WRAP)
                CardViewv7DSL.cardElevation(10.0f)
                DSL.margin(5)
                DSL.gravity(DSL.CENTER_HORIZONTAL)
                DSL.layoutGravity(DSL.CENTER_HORIZONTAL)
                DSL.imageView {
                    DSL.size(DSL.FILL, 400)
                    PicassoLoadDSL.picasso(PicassoLoadDSL.load(model.book.image, R.mipmap.ic_launcher))
                    DSL.layoutGravity(DSL.CENTER)
                    DSL.scaleType(ImageView.ScaleType.CENTER)
                }
            }

            DSL.linearLayout {
                DSL.size(DSL.WRAP, DSL.WRAP)
                DSL.gravity(DSL.CENTER)
                DSL.layoutGravity(DSL.CENTER)
                DSL.orientation(LinearLayout.HORIZONTAL)

                DSL.imageView {
                    DSL.size(50, DSL.WRAP)
                    DSL.imageResource(R.mipmap.ic_launcher)
                    DSL.layoutGravity(DSL.LEFT)
                    DSL.margin(5)
                    DSL.scaleType(ImageView.ScaleType.CENTER_INSIDE)
                }

                DSL.imageView {
                    DSL.size(50, DSL.WRAP)
                    DSL.imageResource(R.mipmap.ic_launcher)
                    DSL.layoutGravity(DSL.LEFT)
                    DSL.margin(5)
                    DSL.scaleType(ImageView.ScaleType.CENTER_INSIDE)
                }

                DSL.button {
                    DSL.size(DSL.WRAP, DSL.WRAP)
                    DSL.text("Lire")
                    DSL.margin(5)
                    DSL.layoutGravity(DSL.CENTER_VERTICAL)
                }
            }

            DSL.textView {
                DSL.size(DSL.WRAP, DSL.WRAP)
                DSL.text(model.book.title)
                DSL.padding(0, DSL.dip(4))
                DSL.layoutGravity(DSL.CENTER_HORIZONTAL)
                DSL.maxLines(3)
                DSL.ellipsize(TextUtils.TruncateAt.END)
                DSL.textSize(DSL.sip(20F))
            }

            DSL.textView {
                DSL.size(DSL.WRAP, DSL.WRAP)
                DSL.text(model.book.authors.joinToString { author -> author.name })
                DSL.padding(0, DSL.dip(4))
                DSL.layoutGravity(DSL.RIGHT)
                DSL.maxLines(3)
                DSL.ellipsize(TextUtils.TruncateAt.END)
                DSL.textSize(DSL.sip(16F))
            }
        }
    }
}

fun buildMapCounterToCounterViewModel(store: Store<ApplicationState>): (Book) -> BookViewModel {
    return { book ->
        BookViewModel(
                book,
                { store.dispatch(BOOK_CLICKED(book.id)) }
        )
    }
}

data class BookViewModel(val book: Book, val clickHandler: () -> Unit)
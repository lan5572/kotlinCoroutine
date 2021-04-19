package com.carman.kotlin.coroutine.extensions

import android.content.Context
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import coil.Coil.imageLoader
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.imageLoader
import coil.loadAny
import coil.request.Disposable
import coil.request.ImageRequest
import coil.size.PixelSize
import coil.target.PoolableViewTarget
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation
import coil.transition.TransitionTarget

inline fun ImageRequest.Builder.configPlaceholder(@DrawableRes placeholder: Int) {
    if (placeholder != 0) {
        placeholder(placeholder)
    }
}

inline fun ImageRequest.Builder.configErrorHolder(@DrawableRes errorId: Int) {
    if (errorId != 0) {
        error(errorId)
    }
}

inline fun getGifImageLoader(context:Context):ImageLoader{
    return ImageLoader.Builder(context).componentRegistry {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            add(ImageDecoderDecoder())
        } else {
            add(GifDecoder())
        }
    }.build()
}


inline fun getSvgImageLoader(context:Context):ImageLoader{
    return ImageLoader.Builder(context).componentRegistry {
            add(SvgDecoder(context))
    }.build()
}

fun ImageView.load(
    source: Any?,
    @DrawableRes placeholder: Int = 0,
    @DrawableRes errorId: Int = 0,
    skipMemoryCache: Boolean = false,
    isGrayscale: Boolean = false
) {
    loadAny(source) {
        configPlaceholder(placeholder)
        configErrorHolder(errorId)
        if (isGrayscale) {
            transformations(
                GrayscaleTransformation()
            )
        }
    }
}

fun ImageView.loadGif(
        source: Any?,
        @DrawableRes placeholder: Int = 0,
        @DrawableRes errorId: Int = 0,
        skipMemoryCache: Boolean = false,
        isGrayscale: Boolean = false,
) {
    loadAny(source, getGifImageLoader(context)) {
        configPlaceholder(placeholder)
        configErrorHolder(errorId)
        if (isGrayscale) {
            transformations(
                GrayscaleTransformation()
            )
        }
    }
}


fun ImageView.loadCircle(
    source: Any?,
    @DrawableRes placeholder: Int = 0,
    @DrawableRes errorId: Int = 0,
    skipMemoryCache: Boolean = false,
    isGrayscale: Boolean = false
): Disposable {
    return loadAny(source) {
        configPlaceholder(placeholder)
        configErrorHolder(errorId)
        if (isGrayscale) {
            transformations(
                CircleCropTransformation(), GrayscaleTransformation()
            )
        } else {
            transformations(
                CircleCropTransformation()
            )
        }
    }
}

fun ImageView.loadRoundedCorner(
    source: Any?,
    @DrawableRes placeholder: Int = 0,
    @DrawableRes errorId: Int = 0,
    skipMemoryCache: Boolean = false,
    isGrayscale: Boolean = false,
    radius: Int = 0
): Disposable {
    return loadRoundedCorner(
        source,
        errorId,
        placeholder,
        skipMemoryCache,
        isGrayscale,
        radius.toFloat(),
        radius.toFloat(),
        radius.toFloat(),
        radius.toFloat()
    )
}

fun ImageView.loadRoundedCorner(
    source: Any?,
    @DrawableRes placeholder: Int = 0,
    @DrawableRes errorId: Int = 0,
    skipMemoryCache: Boolean = false,
    isGrayscale: Boolean = false,
    topLeft: Float = 0f,
    topRight: Float = 0f,
    bottomRight: Float = 0f,
    bottomLeft: Float = 0f
): Disposable {
    return loadAny(source) {
        configPlaceholder(placeholder)
        configErrorHolder(errorId)
        if (isGrayscale) {
            transformations(
                CircleCropTransformation(), GrayscaleTransformation()
            )
        } else {
            transformations(
                RoundedCornersTransformation(topLeft, topRight, bottomLeft, bottomRight)
            )
        }
    }
}


fun ImageView.loadRoundedCorner(
    source: Any?,
    @DrawableRes placeholder: Int = 0,
    @DrawableRes errorId: Int = 0,
    skipMemoryCache: Boolean = false,
    isGrayscale: Boolean = false,
    width: Int,
    height: Int,
    radius: Int = 0
): Disposable {
    return loadRoundedCorner(
        source,
        errorId,
        placeholder,
        skipMemoryCache,
        isGrayscale,
        width,
        height,
        radius.toFloat(),
        radius.toFloat(),
        radius.toFloat(),
        radius.toFloat()
    )
}


fun ImageView.loadRoundedCorner(
    source: Any?,
    @DrawableRes placeholder: Int = 0,
    @DrawableRes errorId: Int = 0,
    skipMemoryCache: Boolean = false,
    isGrayscale: Boolean = false,
    width: Int,
    height: Int,
    topLeft: Float = 0f,
    topRight: Float = 0f,
    bottomRight: Float = 0f,
    bottomLeft: Float = 0f
): Disposable {
    return loadAny(source) {
        configPlaceholder(placeholder)
        configErrorHolder(errorId)
        size(PixelSize(width, height))
        if (isGrayscale) {
            transformations(
                CircleCropTransformation(), GrayscaleTransformation()
            )
        } else {
            transformations(
                RoundedCornersTransformation(topLeft, topRight, bottomLeft, bottomRight)
            )
        }
    }
}


fun View.load(
    source: Any?,
    @DrawableRes placeholder: Int = 0,
    @DrawableRes errorId: Int = 0,
    skipMemoryCache: Boolean = false,
    isGrayscale: Boolean = false
): Disposable {
    val builder: ImageRequest.Builder.() -> Unit = {
        configPlaceholder(placeholder)
        configErrorHolder(errorId)
        if (isGrayscale) {
            transformations(
                GrayscaleTransformation()
            )
        }
    }
    val request = ImageRequest.Builder(context)
        .data(source)
        .target(CoilViewTarget(this))
        .apply(builder)
        .build()
    return context.imageLoader.enqueue(request)
}


fun View.loadRoundedCorner(
    source: Any?,
    @DrawableRes placeholder: Int = 0,
    @DrawableRes errorId: Int = 0,
    skipMemoryCache: Boolean = false,
    isGrayscale: Boolean = false,
    radius: Int = 0
): Disposable {
    return loadRoundedCorner(
        source,
        errorId,
        placeholder,
        skipMemoryCache,
        isGrayscale,
        radius.toFloat(),
        radius.toFloat(),
        radius.toFloat(),
        radius.toFloat()
    )
}

fun View.loadRoundedCorner(
    source: Any?,
    @DrawableRes placeholder: Int = 0,
    @DrawableRes errorId: Int = 0,
    skipMemoryCache: Boolean = false,
    isGrayscale: Boolean = false,
    topLeft: Float = 0f,
    topRight: Float = 0f,
    bottomRight: Float = 0f,
    bottomLeft: Float = 0f
): Disposable {
    val builder: ImageRequest.Builder.() -> Unit = {
        configPlaceholder(placeholder)
        configErrorHolder(errorId)
        if (isGrayscale) {
            transformations(
                CircleCropTransformation(), GrayscaleTransformation()
            )
        } else {
            transformations(
                RoundedCornersTransformation(topLeft, topRight, bottomLeft, bottomRight)
            )
        }
    }
    val request = ImageRequest.Builder(context)
        .data(source)
        .target(CoilViewTarget(this))
        .apply(builder)
        .build()
    return context.imageLoader.enqueue(request)
}


fun View.loadRoundedCorner(
    source: Any?,
    @DrawableRes placeholder: Int = 0,
    @DrawableRes errorId: Int = 0,
    skipMemoryCache: Boolean = false,
    isGrayscale: Boolean = false,
    width: Int,
    height: Int,
    radius: Int = 0
): Disposable {
    return loadRoundedCorner(
        source,
        errorId,
        placeholder,
        skipMemoryCache,
        isGrayscale,
        width,
        height,
        radius.toFloat(),
        radius.toFloat(),
        radius.toFloat(),
        radius.toFloat()
    )
}


fun View.loadRoundedCorner(
    source: Any?,
    @DrawableRes placeholder: Int = 0,
    @DrawableRes errorId: Int = 0,
    skipMemoryCache: Boolean = false,
    isGrayscale: Boolean = false,
    width: Int,
    height: Int,
    topLeft: Float = 0f,
    topRight: Float = 0f,
    bottomRight: Float = 0f,
    bottomLeft: Float = 0f
): Disposable {
    val builder: ImageRequest.Builder.() -> Unit = {
        configPlaceholder(placeholder)
        configErrorHolder(errorId)
        size(PixelSize(width, height))
        if (isGrayscale) {
            transformations(
                CircleCropTransformation(), GrayscaleTransformation()
            )
        } else {
            transformations(
                RoundedCornersTransformation(topLeft, topRight, bottomLeft, bottomRight)
            )
        }
    }
    val request = ImageRequest.Builder(context)
        .data(source)
        .target(CoilViewTarget(this))
        .apply(builder)
        .build()
    return context.imageLoader.enqueue(request)
}

class CoilViewTarget(
    override val view: View
) : PoolableViewTarget<View>, TransitionTarget, DefaultLifecycleObserver {

    private var isStarted = false

    override val drawable: Drawable? get() = view.background

    override fun onStart(placeholder: Drawable?) = setDrawable(placeholder)

    override fun onError(error: Drawable?) = setDrawable(error)

    override fun onSuccess(result: Drawable) = setDrawable(result)

    override fun onClear() = setDrawable(null)

    override fun onStart(owner: LifecycleOwner) {
        isStarted = true
        updateAnimation()
    }

    override fun onStop(owner: LifecycleOwner) {
        isStarted = false
        updateAnimation()
    }

    /** Replace the [ImageView]'s current drawable with [drawable]. */
    protected open fun setDrawable(drawable: Drawable?) {
        (view.background as? Animatable)?.stop()
        view.background = drawable
        updateAnimation()
    }

    /** Start/stop the current [Drawable]'s animation based on the current lifecycle state. */
    protected open fun updateAnimation() {
        val animatable = view.background as? Animatable ?: return
        if (isStarted) animatable.start() else animatable.stop()
    }

    override fun equals(other: Any?): Boolean {
        return (this === other) || (other is CoilViewTarget && view == other.view)
    }

    override fun hashCode() = view.hashCode()

    override fun toString() = "CoilViewTarget(view=$view)"
}

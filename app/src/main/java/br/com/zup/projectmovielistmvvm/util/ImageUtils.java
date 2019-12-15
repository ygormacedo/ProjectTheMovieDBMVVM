package br.com.zup.projectmovielistmvvm.util;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class ImageUtils {

    public static void loadImage(final String url, final ImageView imageView, final ProgressBar progressBar, final int defaultImage) {
        progressBar.setVisibility(View.VISIBLE);

        if (url == null) {
            imageView.setImageResource(defaultImage);
            return;
        }

        Picasso.get()
                .load(url)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .transform(new BitmapTranform(1024, 768))
                .resize(512, 512)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        progressBar.setVisibility(View.VISIBLE);
                        Picasso.get().load(url).networkPolicy(NetworkPolicy.NO_CACHE)
                                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_CACHE).error(defaultImage)
                                .transform(new BitmapTranform(1024, 768))
                                .resize(512, 512)
                                .into(imageView, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        progressBar.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onError(Exception e) {
                                        progressBar.setVisibility(View.VISIBLE);
                                    }
                                });
                    }
                });
    }

    static class BitmapTranform implements Transformation {
        private int maxWidth;
        private int maxHeight;

        BitmapTranform(int maxWidth, int maxHeight) {
            this.maxWidth = maxWidth;
            this.maxHeight = maxHeight;
        }

        @Override
        public Bitmap transform(Bitmap source) {
            int targetWidth;
            int targetHeight;
            double aspectRatic;
            if (source.getWidth() > source.getHeight()) {
                targetWidth = maxWidth;
                aspectRatic = (double) source.getHeight() / (double) source.getWidth();
                targetHeight = (int) (targetWidth * aspectRatic);
            } else {
                targetHeight = maxHeight;
                aspectRatic = (double) source.getWidth() / (double) source.getHeight();
                targetWidth = (int) (targetHeight * aspectRatic);
            }
            Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override
        public String key() {
            return maxWidth + "x" + maxHeight;
        }
    }
}

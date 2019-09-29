package com.doubletapp.psbhack.main_npd

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import com.doubletapp.psbhack.R
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.dialog_share.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class BillDialog : DialogFragment() {

    companion object {

        const val TAG = "BillDialog"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_share, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val background = ColorDrawable(Color.TRANSPARENT)
        val margin = 25
        dialog?.window?.setBackgroundDrawable(InsetDrawable(background, margin, 0, margin, 0))

        generateCode(link_text.text.toString(), BarcodeFormat.QR_CODE, 150, 150)

        copy_btn.setOnClickListener {
            onClickCopy(link_text.text.toString())
        }

        cancel.setOnClickListener {
            dialog?.dismiss()
            activity?.onBackPressed()
        }

        share_btn.setOnClickListener {
            shareQR()
        }


//        button.setOnClickListener {
//            dialog?.dismiss()
//            context?.let {
//
////                startActivity(MainNpdActivity.getIntent(it, true))
//            }
//        }
    }

    private fun onClickCopy(text: String) {
        val clipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clip = android.content.ClipData.newPlainText("address", text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Скопировано!", Toast.LENGTH_SHORT).show()
    }

    private fun shareQR() {
        val bitmap = (barcode.getDrawable() as BitmapDrawable).bitmap
        try {
            val cachePath = File(context!!.getCacheDir(), "images")
            cachePath.mkdirs() // don't forget to make the directory
            val stream = FileOutputStream(cachePath.path + "/image.png") // overwrites this image every time
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val imagePath = File(context!!.cacheDir, "images")
        val newFile = File(imagePath, "image.png")
        val contentUri = FileProvider.getUriForFile(context!!, "com.doubletapp.psbhack.provider",
                newFile)

        if (contentUri != null) {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, link_text.text.toString())
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
            startActivity(Intent.createChooser(shareIntent, getString(R.string.share)))
        }
    }

    fun generateCode(data: String?, format: BarcodeFormat, width: Int, height: Int) {
        val writer = QRCodeWriter()
        if (data == null) return
//        Thread {
            try {
                val bm = writer.encode(data, format, width, height)
                val imageBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

                for (i in 0 until width) { //width
                    for (j in 0 until height) { //height
                        imageBitmap.setPixel(i, j, if (bm.get(i, j)) Color.BLACK else Color.WHITE)
                    }
                }

                barcode.setImageBitmap(imageBitmap)

            } catch (ex: WriterException) {
                ex.printStackTrace()
//                listener.onGenerateError()
            }
//        }.start()
    }
}
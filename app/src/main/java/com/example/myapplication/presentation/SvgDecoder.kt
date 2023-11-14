package com.example.myapplication.presentation

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder
import com.caverock.androidsvg.SVG
import java.io.InputStream

class SvgDecoder: ResourceDecoder<InputStream, SVG>, ResourceTranscoder<InputStream, SVG> {

    override fun handles(source: InputStream, options: Options): Boolean {
        TODO()
    }

    override fun decode(
        source: InputStream,
        width: Int,
        height: Int,
        options: Options
    ): Resource<SVG> {
        val svg = SVG.getFromInputStream(source)
        return SimpleResource(svg)
    }

    override fun transcode(toTranscode: Resource<InputStream>, options: Options): Resource<SVG> {
        TODO()
    }
}
package com.zsank.chero

import android.net.Uri

class Music(
	val id: Long,
	val img: Int = 0,
	val title: String = "",
	val displayName: String = "",
	val artist: String = "",
	val album: String = "",
	val uri: Uri,
	val duration: Long = 0,
	val size: Int,
	val thumbnailURI: Uri
)
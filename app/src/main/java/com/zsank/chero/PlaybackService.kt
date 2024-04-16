package com.zsank.chero

import android.content.Intent
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@UnstableApi
@AndroidEntryPoint
class PlaybackService @Inject constructor() : MediaSessionService() {
	private var mediaSession: MediaSession? = null
	
	@Inject
	lateinit var query: MusicQuery
	
	// Create your player and media session in the onCreate lifecycle event
	override fun onCreate() {
		super.onCreate()
		val player = ExoPlayer.Builder(this).build()
		mediaSession = MediaSession.Builder(this, player).build()
		val musicList = query.queryAudioFilesWithPaging(0, 1000)
		val mediaItems = musicList.map { MediaItem.fromUri(it.uri) }
		player.addMediaItems(mediaItems)
	}
	
	override fun onTaskRemoved(rootIntent: Intent?) {
		mediaSession?.let {
			val player = mediaSession!!.player
			if (player.playWhenReady) {
				player.pause()
			}
		}
		stopSelf()
	}
	
	override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? =
		mediaSession
	
	override fun onDestroy() {
		mediaSession?.run {
			player.release()
			release()
			mediaSession = null
		}
		super.onDestroy()
	}
}
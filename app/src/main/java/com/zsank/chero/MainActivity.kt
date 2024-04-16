package com.zsank.chero

import android.content.ComponentName
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.zsank.chero.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	
	private lateinit var controllerFuture: ListenableFuture<MediaController>
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}
	
	@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
	override fun onStart() {
		super.onStart()
		val sessionToken = SessionToken(this, ComponentName(this, PlaybackService::class.java))
		controllerFuture = MediaController.Builder(this, sessionToken).buildAsync()
		controllerFuture.addListener(
			{
				binding.musicMedia3Player.setPlayer(controllerFuture.get())
				controllerFuture.get().prepare()
				controllerFuture.get().play()
				bindPlayPauseBut()
			},
			MoreExecutors.directExecutor()
		)
		
	}
	
	override fun onStop() {
		super.onStop()
		MediaController.releaseFuture(controllerFuture)
	}
	
	private fun bindPlayPauseBut() {
		binding.playPauseBut.setOnClickListener {
			if (controllerFuture.get().isPlaying) {
				controllerFuture.get().pause()
			} else controllerFuture.get().play()
		}
	}
}
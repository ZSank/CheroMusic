plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	kotlin("kapt")
	id("com.google.dagger.hilt.android")
}

android {
	namespace = "com.zsank.chero"
	compileSdk = 34
	
	buildFeatures {
		viewBinding = true
	}
	defaultConfig {
		applicationId = "com.zsank.chero"
		minSdk = 24
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"
		
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
	
	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	kotlinOptions {
		jvmTarget = "17"
	}
}

dependencies {
	
	implementation("androidx.core:core-ktx:1.9.0")
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("com.google.android.material:material:1.11.0")
	implementation("androidx.constraintlayout:constraintlayout:2.1.4")
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
	
	//Hilt
	implementation("com.google.dagger:hilt-android:2.44")
	kapt("com.google.dagger:hilt-android-compiler:2.44")
	
	val media3Version = "1.1.0"
	//Exoplayer
	implementation("androidx.media3:media3-exoplayer:$media3Version")
	implementation("androidx.media3:media3-ui:$media3Version")
	// For exposing and controlling media sessions
	implementation("androidx.media3:media3-session:$media3Version")
}
// Allow references to generated code
kapt {
	correctErrorTypes = true
}
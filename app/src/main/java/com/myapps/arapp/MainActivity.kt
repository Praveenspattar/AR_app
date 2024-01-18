package com.myapps.arapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var sceneView: ArSceneView
    private lateinit var placeButton: ExtendedFloatingActionButton
    private lateinit var modelNode: ArModelNode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sceneView = findViewById(R.id.sceneView)
        placeButton = findViewById(R.id.place)

        placeButton.setOnClickListener {
            placeModel()
        }

//        MainScope().launch {
//            loadModel()
//        }

        modelNode = ArModelNode().apply {
            loadModelGlbAsync(
                glbFileLocation = "models/ganeshji.glb"
            ){
                sceneView.isVisible = true
            }
            onAnchorChanged = {placeButton.isGone}
        }
        sceneView.addChild(modelNode)

    }

    private fun placeModel() {
        modelNode.anchor()
    }

//    private suspend fun loadModel() {
//        modelNode = ArModelNode().apply {
//            loadModelGlb(
//                this@MainActivity,
//                glbFileLocation = "models/ganeshji.glb"
//            ) {
//                // Optional: Set any additional properties or callbacks upon model loading completion
//                sceneView.planeRenderer.isVisible = true
//            }
//            onAnchorChanged = {placeButton.isGone}
//        }
//
//        // Add the modelNode to the sceneView after loading
//        sceneView.addChild(modelNode)
//    }
}

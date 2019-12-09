package com.example.myapplication2;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class StorageActivity {
    FirebaseStorage storage = FirebaseStorage.getInstance();
    // Create a storage reference from our app
    StorageReference storageRef = storage.getReference();
    // Create a child reference
// imagesRef now points to "images"
    StorageReference imagesRef = storageRef.child("images");

    // Child references can also take paths
// spaceRef now points to "images/space.jpg
// imagesRef still points to "images"
    StorageReference spaceRef = storageRef.child("images/space.jpg");


}

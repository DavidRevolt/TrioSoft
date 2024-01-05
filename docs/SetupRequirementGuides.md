# Setup Requirement Guide
  
## Guide

## Firebase Cloud Firestore rules example
To write rules go to Cloud Firestore console and press "Switch from Datastore mode to Native mode".
```
service cloud.firestore {
  match /databases/{database}/documents {
    match /{document=**} {
      allow read, write: if request.auth != null;
    }
  }
}
```

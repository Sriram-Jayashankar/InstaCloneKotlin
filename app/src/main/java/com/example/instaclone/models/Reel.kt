package com.example.instaclone.models

class Reel {
    var reelUrl:String=""
    var caption:String=""
    constructor()
    constructor(postUrl: String, caption: String) {
        this.reelUrl = postUrl
        this.caption = caption
    }
}
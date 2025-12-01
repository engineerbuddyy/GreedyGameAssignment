package com.example.greedygamequoteappassignment.data

data class BannerModel(
    val url:String
)


object BannerData{
    fun getBanners():List<BannerModel> {
        return listOf(
            BannerModel(
                url = "https://i.pinimg.com/1200x/06/3c/54/063c54d98d276a6a2cc0db2bfe390250.jpg"
            ),
            BannerModel(
                url = "https://i.pinimg.com/736x/1a/fd/3f/1afd3f3fd73871816c92cf7cdbbd449f.jpg"
            ),
            BannerModel(
                url = "https://i.pinimg.com/736x/6c/77/3a/6c773a6a203b8c0a9d63ec3a2564a639.jpg"
            ),
            BannerModel(
                url = "https://i.pinimg.com/736x/c8/aa/e6/c8aae66a45578bd0dacaae465d356f50.jpg"
            ),
            BannerModel(
                url = "https://i.pinimg.com/736x/bc/88/cc/bc88cce1a73ca78152babaffa0ad7ff4.jpg"
            ),
            BannerModel(
                url = "https://i.pinimg.com/1200x/70/75/84/707584a757f180697672acb9566ba389.jpg"
            ),
        )
    }
}
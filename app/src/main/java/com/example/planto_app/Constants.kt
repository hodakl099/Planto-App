package com.example.planto_app

import android.content.Context

class Constants(context: Context) {

    companion object{
        val KEY = "currentPlant"
        val DAILY = "Daily"
        val WEEKLY = "Weekly"
        val MONTHLY = "Monthly"
        val PLANT_ARTICLE_URL =
            "https://www.google.com/search?q=plant+articles&rlz=1C1CHZN_enLY1027LY1027&biw=1920&bih=969&tbm=nws&sxsrf=ALiCzsZvxG02qne00nUGzGzcwMKGnaJ7mQ%3A1667850601444&ei=aWFpY7HkGpWHxc8PvIqu8AQ&oq=plant+art&gs_lcp=Cgxnd3Mtd2l6LW5ld3MQAxgBMgUIABCRAjIFCAAQkQIyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDoFCCEQoAE6BwgAEIAEEBM6BggAEB4QEzoKCAAQBRAeEAoQEzoHCAAQgAQQAToHCAAQgAQQDToKCAAQsQMQgwEQQzoICAAQgAQQsQM6CwgAEIAEELEDEIMBOggIABCxAxCDAToECAAQQ1DMB1joLGD2OWgFcAB4BYABoASIAdAgkgEMMC4xMS41LjIuMC4xmAEAoAEBsAEAwAEB&sclient=gws-wiz-news"
        val PLANT_IDENTIFY_URL =
            "https://www.google.com/search?q=plant+identification+google+lens&rlz=1C1CHZN_enLY1027LY1027&sxsrf=ALiCzsafsKYA8a4PRbru2NXiHjn_oai6nA%3A1667856507949&ei=e3hpY4vLOa6N9u8Psvy1-AM&ved=0ahUKEwjL9MTVgZ37AhWuhv0HHTJ-DT8Q4dUDCA8&uact=5&oq=plant+identication+google+lens&gs_lcp=Cgxnd3Mtd2l6LXNlcnAQAzIHCAAQgAQQDTIFCAAQhgMyBQgAEIYDMgUIABCGAzIFCAAQhgMyBQgAEIYDOgoIABBHENYEELADOgcIABCABBAKOggIABAWEB4QDzoGCAAQFhAeOggIABAIEB4QDUoECEEYAEoECEYYAFCSAViwD2CQEWgBcAB4AIAB4AGIAeYPkgEGMC4xMS4xmAEAoAEByAEIwAEB&sclient=gws-wiz-serp"
        val PLANT_SHOP =
            "https://www.google.com/search?q=plant+articles&rlz=1C1CHZN_enLY1027LY1027&biw=1920&bih=969&tbm=nws&sxsrf=ALiCzsZvxG02qne00nUGzGzcwMKGnaJ7mQ%3A1667850601444&ei=aWFpY7HkGpWHxc8PvIqu8AQ&oq=plant+art&gs_lcp=Cgxnd3Mtd2l6LW5ld3MQAxgBMgUIABCRAjIFCAAQkQIyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDoFCCEQoAE6BwgAEIAEEBM6BggAEB4QEzoKCAAQBRAeEAoQEzoHCAAQgAQQAToHCAAQgAQQDToKCAAQsQMQgwEQQzoICAAQgAQQsQM6CwgAEIAEELEDEIMBOggIABCxAxCDAToECAAQQ1DMB1joLGD2OWgFcAB4BYABoASIAdAgkgEMMC4xMS41LjIuMC4xmAEAoAEBsAEAwAEB&sclient=gws-wiz-news"
    }

    val PLANT_LOCATION = arrayOf(
        context.getString(R.string.balcony),
        context.getString(R.string.hallway),
        context.getString(R.string.living_room),
        context.getString(R.string.kitchen),
        context.getString(R.string.bedroom)
    )



    val PLANT_TYPE = arrayOf(
        context.getString(R.string.climbers),
        context.getString(R.string.creepers),
        context.getString(R.string.shrubs),
        context.getString(R.string.trees),
        context.getString(R.string.herbs)
    )

    val WATERING_SCHEDUELE = arrayOf(
        context.getString(R.string.daily),
        context.getString(R.string.weekly),
        context.getString(R.string.monthly)
    )




}
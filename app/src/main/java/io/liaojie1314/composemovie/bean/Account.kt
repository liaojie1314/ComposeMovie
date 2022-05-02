package io.liaojie1314.composemovie.bean

import io.liaojie1314.composemovie.R

data class Account (
    var Post: Int,
    var FullName: Int,
    var About: Int,
    var NickName: Int,
    var Status: Int,
    var Sns: Int
)

val myAccount: Account = Account(
    R.drawable.ic_profile_image,
    R.string.full_name,
    R.string.about_content,
    R.string.nick_name,
    R.string.status_mine,
    R.string.sns_mine
)
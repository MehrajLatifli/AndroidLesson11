package com.example.androidlesson11.Models

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product ( @DrawableRes val drawableimage:Int?=null,  val productname:String?=null,  val raiting: Double?=null,  val newprice:Double?=null,  val oldprice:Double?=null,  val discount: Double?=null): Parcelable
{

}
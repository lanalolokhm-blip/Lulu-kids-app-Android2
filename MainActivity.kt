package com.lulu.kids
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalLayoutDirection
import java.util.Locale
private val Cream=Color(0xFFFFF8F0); private val Gold=Color(0xFFC99B52); private val Ink=Color(0xFF493C36); private val Pink=Color(0xFFE9B7B0); private val Mint=Color(0xFFB9D9C5); private val Blue=Color(0xFFB9D4E8)
class MainActivity:ComponentActivity(){ private var tts:TextToSpeech?=null; override fun onCreate(b:Bundle?){super.onCreate(b);tts=TextToSpeech(this){tts?.language=Locale("ar")};setContent{LuluApp{tts?.speak(it,TextToSpeech.QUEUE_FLUSH,null,"lulu")}}};override fun onDestroy(){tts?.shutdown();super.onDestroy()}}
@Composable fun LuluApp(speak:(String)->Unit){var page by remember{mutableStateOf("home")};var stars by remember{mutableIntStateOf(12)};CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl){Surface(Modifier.fillMaxSize(),color=Cream){when(page){"home"->Home(stars){page=it};"letters"->Letters({stars+=2},speak){page="home"};"colors"->Colors({stars+=2}){page="home"};"numbers"->Numbers({stars+=2},speak){page="home"};"parent"->Parent(stars){page="home"}}}}}
@Composable fun Header(stars:Int){Row(Modifier.fillMaxWidth().padding(20.dp),Arrangement.SpaceBetween,Alignment.CenterVertically){Text("⭐ $stars",color=Gold,fontSize=18.sp,fontWeight=FontWeight.Bold);Text("لولو",color=Ink,fontSize=30.sp,fontWeight=FontWeight.Bold)}}
@Composable fun Home(stars:Int,go:(String)->Unit){Column(Modifier.fillMaxSize().padding(18.dp),horizontalAlignment=Alignment.CenterHorizontally){Header(stars);Card(Modifier.fillMaxWidth(),shape=RoundedCornerShape(28.dp),colors=CardDefaults.cardColors(Color.White)){Column(Modifier.padding(24.dp),horizontalAlignment=Alignment.CenterHorizontally){Text("مرحباً بك في عالم لولو ✨",color=Ink,fontSize=25.sp,fontWeight=FontWeight.Bold,textAlign=TextAlign.Center);Text("نتعلم ونلعب ونكتشف معاً!",color=Ink.copy(alpha=.75f),fontSize=16.sp);Text("👧🏻  🐰",fontSize=58.sp,modifier=Modifier.padding(12.dp))}};Spacer(Modifier.height(18.dp));Text("ماذا نتعلم اليوم؟",color=Ink,fontSize=21.sp,fontWeight=FontWeight.Bold);Spacer(Modifier.height(10.dp));Row(Modifier.fillMaxWidth(),Arrangement.spacedBy(10.dp)){Tile("🔤","الحروف",Pink){go("letters")};Tile("🎨","الألوان",Mint){go("colors")}};Spacer(Modifier.height(10.dp));Row(Modifier.fillMaxWidth(),Arrangement.spacedBy(10.dp)){Tile("🔢","الأرقام",Blue){go("numbers")};Tile("👨‍👩‍👧","ولي الأمر",Color(0xFFE8D9C2)){go("parent")}}}}
@Composable fun Tile(icon:String,title:String,bg:Color,onClick:()->Unit){Card(Modifier.weight(1f).clickable{onClick()},colors=CardDefaults.cardColors(bg),shape=RoundedCornerShape(22.dp)){Column(Modifier.padding(18.dp),horizontalAlignment=Alignment.CenterHorizontally){Text(icon,fontSize=34.sp);Text(title,color=Ink,fontSize=17.sp,fontWeight=FontWeight.Bold)}}}
@Composable fun Top(title:String,back:()->Unit){Row(Modifier.fillMaxWidth().padding(18.dp),verticalAlignment=Alignment.CenterVertically){Text("‹",fontSize=38.sp,color=Gold,modifier=Modifier.clickable{back()});Spacer(Modifier.width(12.dp));Text(title,color=Ink,fontSize=25.sp,fontWeight=FontWeight.Bold)}}
@Composable fun Letters(done:()->Unit,speak:(String)->Unit,back:()->Unit){Column(Modifier.fillMaxSize()){Top("الحروف العربية",back);Text("اضغط على الحرف واسمع صوته",color=Ink,modifier=Modifier.padding(horizontal=20.dp));val ls=listOf("أ","ب","ت","ث","ج","ح","خ","د","ر","س","ش","ص");Column(Modifier.padding(18.dp),verticalArrangement=Arrangement.spacedBy(10.dp)){ls.chunked(4).forEach{r->Row(Modifier.fillMaxWidth(),Arrangement.spacedBy(10.dp)){r.forEach{l->Tile(l,"حرف $l",Color.White){speak(l);done()}}}}}}}
@Composable fun Colors(done:()->Unit,back:()->Unit){Column(Modifier.fillMaxSize()){Top("الألوان",back);Text("تعرّف على الألوان",color=Ink,modifier=Modifier.padding(horizontal=20.dp));listOf("أحمر" to Color(0xFFE8A6A1),"أخضر" to Mint,"أزرق" to Blue,"أصفر" to Color(0xFFF4D37B)).forEach{(n,c)->Card(Modifier.fillMaxWidth().padding(horizontal=20.dp,vertical=6.dp).clickable{done()},colors=CardDefaults.cardColors(c),shape=RoundedCornerShape(20.dp)){Text(n,Modifier.padding(22.dp).fillMaxWidth(),color=Ink,fontSize=22.sp,fontWeight=FontWeight.Bold,textAlign=TextAlign.Center)}}}}
@Composable fun Numbers(done:()->Unit,speak:(String)->Unit,back:()->Unit){Column(Modifier.fillMaxSize()){Top("الأرقام",back);Text("اضغط على الرقم واسمعه",color=Ink,modifier=Modifier.padding(horizontal=20.dp));Row(Modifier.padding(20.dp),Arrangement.spacedBy(10.dp)){listOf("١","٢","٣","٤","٥").forEach{n->Tile("⭐",n,Color.White){speak(n);done()}}}}}
@Composable fun Parent(stars:Int,back:()->Unit){Column(Modifier.fillMaxSize()){Top("منطقة ولي الأمر",back);Column(Modifier.padding(22.dp),verticalArrangement=Arrangement.spacedBy(14.dp)){Card(colors=CardDefaults.cardColors(Color.White),shape=RoundedCornerShape(24.dp)){Column(Modifier.padding(22.dp)){Text("تقدم الطفل",fontSize=23.sp,fontWeight=FontWeight.Bold,color=Ink);Text("⭐ النجوم: $stars",fontSize=18.sp,color=Gold,modifier=Modifier.padding(top=10.dp));Text("🎯 المستوى: ${1+stars/20}",fontSize=18.sp,color=Ink,modifier=Modifier.padding(top=6.dp))}};Text("اقتراح اليوم: 10 دقائق حروف ثم لعبة ألوان.",color=Ink,fontSize=17.sp)}}}

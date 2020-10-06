# tomato_Clock

## 鼎鼎大名的番茄鐘APP

番茄專案讚啦XD

<img src="https://github.com/Maxspace1024/PRJ_tomato_Clock/blob/master/code/tomato.png" width="150"/>

想說碰碰Android，有一陣子沒練習了<br/>
也複習一下最近練習GIT(CommandLine)的成果<br/><br/>
附上APP螢幕截圖<br/>
<img src="https://github.com/Maxspace1024/PRJ_tomato_Clock/blob/master/shots/shots_01.png" width="300"/><br/>
點擊中間的番茄<br/>
計時器就會開始倒數<br/>
也可以個人化設定時間<br/>

<br/>

## 備註一些Android Code生疏的地方


#### 常用method
```java
//型態轉換
String.valueOf(/*NUMBER*/);
Long.valueOf(/*STRING*/);

//格式化輸出
String.format(/*FORMAT STR*/, /*ARG1*/,/*...*/);

//套用顏色
Color.parseColor(/*#RRGGBB*/);
```

#### Toast使用方法
```java
Toast.makeToast( getApplicationContext()	//here can not use "this"
		 ,/*MESSAGE*/
		 ,/*TIME_DURATION*/).show();
```
記得要秀出吐司www<br/>

#### CountDownTimer使用方法
```java
CountDownTimer cdt = new CountDownTimer(/*Time_CountDown*/,/*Tick_Period*/){

	@Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
	}

};

cdt.start();				//記得啟動倒數計時器

```
創立一個CountDownTimer物件<br/><br/>
參數有兩個
* 倒ˋ數ˇ時間長度 Time_CountDown (ms)
* Tick的週期   Tick_Peroid (ms)

## 新東西

#### Vibrator
首先必須在`AndroidManifest.xml`加入取得權限:
```xml
<uses-permission android:name="android.permission.VIBRATE" />
```
與 <b>application</b> 同一層

```java
Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

vibrator.vibrate(/*DURATION*/);			//unit is ms
//or
vibrator.vibrate(/*PATTERN*/,/*REPEAT*/);
//long array {rest ,vibrate ,...} ,unit is ms
//REPEAT 0(循環) -1(一次)
```
#### cancel
```java
vibrator.cancel();
cdt.cancel();
\\這樣就停止了，簡單
```
Vibrator object 盡量不要命名 v<br/>
不然在setOnClickListener會跟 View v 打架

#### Button Enable
```java
btn.setEnable(/*true or false*/);
\\easy~~
```
#### onTouch
使用`setOnTouchListener`<br/>
`MotionEvent event`用來偵測螢幕互動<br/>
`event.getAction()`取得目前螢幕互動狀態

`MotionEvent.ACTION_DOWN`按下<br/>
`MotionEvent.ACTION_UP`放開

#### Animation
`ObjectAnimator.ofFloat()`創建`ObjectAnimator`物件
參數為 ( ??View object , animation type , arg1,arg2,... )
1. `EditView` ,`TextView`,`ImageView`,...
2. `"scaleX"`,`"scaleY"`,`"rotation"`,`"x"`,`"y"`,...
3. floating number


#### git
如果gitk無法顯示中文<br/>
在git commandline 輸入
> git config --global gui.encoding utf-8


## 改進部分
:white_check_mark: 防止EditText輸入為空時，按下SET會導致APP閃退

:white_check_mark: 防止CountDown時更改init_sec

:white_check_mark: 按番茄多下會產生多個CountDownTimer

:white_check_mark: 設計按第二次番茄取消計時

:white_check_mark: 時間到有振動及鬧鈴

:white_check_mark: 新增番茄動畫，增加互動性

~~`:white_square_button:`~~

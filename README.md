# tomato_Clock

## 鼎鼎大名的番茄鐘APP

番茄專案讚啦XD

<img src="https://github.com/Maxspace1024/PRJ_tomato_Clock/blob/master/code/tomato.png" width="150"/>

想說碰碰Android，有一陣子沒練習了<br/>
也複習一下最近練習GIT(CommandLine)的成果<br/><br/>
附上APP螢幕截圖<br/>
<img src="https://github.com/Maxspace1024/PRJ_tomato_Clock/blob/master/shots/shots_01.png" width="300"/><br/>
點擊中間的番茄<br/>
計時器就會開始倒數<br/><br/>

也可以個人化設定時間<br/>

<br/><br/>

## 備註一些Android Code生疏的地方


#### 常用method
```java
//型態轉換
String.valueOf(/*NUMBER*/);
Long.valueOf(/*STRING*/);

//格式化輸出
String.format(/*FORMAT STR, ARG1,...*/);

//套用顏色
Color.parseColor(/*#RRGGBB*/);
```

#### Toast使用方法
```java
Toast.makeToast(	getApplicationContext()
			,/*MSG
			,TIME_DURATION*/).show();
```
記得要秀出吐司www<br/>

#### CountDownTimer使用方法
```java
CountDownTimer cdt = new CountDownTimer(/*Time_CountDown,Tick_Period*/){

	@Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
	}

};

cdt.start();

```
創立一個CountDownTimer物件<br/><br/>
參數有兩個
* 倒ˋ數ˇ時間長度 Time_CountDown (ms)
* Tick的週期   Tick_Peroid (ms)


## 改進部分
- [ ] 防止EditText輸入為空時，按下SET會導致APP閃退
- [ ] 防止CountDown時更改init_sec
- [ ] 按番茄多下會產生多個CountDownTimer
- [ ] 設計按第二次番茄取消計時
- [ ] 時間到有振動及鬧鈴
- [ ] 新增番茄動畫，增加互動性

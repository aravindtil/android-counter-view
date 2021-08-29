# Text Counter View in Android
- A Simple flip animation for only the **numbers** in the given input string
- The text can include any special characters but only the numbers will be animated
- LinearLayout has been used as a ViewGroup
- Used RecyclerViews for each of the numbers in the input text
- Non-Numbers will be added as a normal TextView
- Upon animating, the numbers will be smoothly scrolled to the target position using animation duration and density DPI

# Cool right?
![ani4](https://user-images.githubusercontent.com/19949072/131246900-7025551c-d370-4b84-932a-7fc19bc3ab30.gif)
![ani5](https://user-images.githubusercontent.com/19949072/131247080-3775661d-5f76-4004-9338-cb9caac5e323.gif)
![ani6](https://user-images.githubusercontent.com/19949072/131247085-b6b29855-8f76-482f-bd3e-e0323ac18190.gif)




# Usage

**Step 1: Include Custom View**

```kotlin
<com.aravind.androidcounterview.widget.TextCounterView
            android:layout_width="wrap_content"
            android:layout_height="40sp"
            android:orientation="horizontal"/>
```

**Step 2: Add Custom Attributes**

```kotlin
<com.aravind.androidcounterview.widget.TextCounterView
            android:layout_width="wrap_content"
            android:layout_height="40sp"
            android:orientation="horizontal"
            app:counterTextAppearance="@style/TextAppearance1"
            app:counterTextWidth="32sp"/>
```
- CounterTextAppearnce is a custom style attribute to set the style appearance of the text

```kotlin
<style name="TextAppearance1" parent="">
   <item name="android:textSize">36sp</item>
   <item name="fontFamily">@font/metropolis_bold</item>
   <item name="android:textColor">@color/colorPrimary</item>
</style>
    
```
- CounterTextWidth is a custom dimen attribute to set the width of each character in the text

**Step 3: Set Text and Animate**

```java
tcv3.setText(textToSet = "₹1,345")
tcv3.updateView(animate = true)
    
```

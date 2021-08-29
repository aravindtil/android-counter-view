# Text Counter View in Android
- A Simple flip animation for only the **numbers** in the given input string
- The text can include any special characters but only the numbers will be animated
- LinearLayout has been used as a ViewGroup
- Used RecyclerViews for each of the numbers in the input text
- Non-Numbers will be added as a normal TextView
- Upon animating, the numbers will be smoothly scrolled to the target position using animation duration and density DPI

# Cool right?
![ani1](https://user-images.githubusercontent.com/19949072/131233509-a506275c-ecc4-4ad5-a483-f159d01a1169.gif) 

![ani2](https://user-images.githubusercontent.com/19949072/131233525-2f17ce27-b084-430a-93d4-c51ea4af3bbf.gif) 

![ani3](https://user-images.githubusercontent.com/19949072/131233538-1a10fe12-c6e8-4efc-8e3d-318934078f42.gif)



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

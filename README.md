# ViewPagerIndicator
Provides a customizable View pager indicator, just need to pass the page numbers and
select/deselect colors or select/deselect drawables can also be provided.



# Getting Started

Add a gradle dependency

      # Root Level Gradle Dependency
      allprojects {
        repositories { 
            jcenter()
            maven { url "https://jitpack.io" }
        }
      }
      
      # Module Level Gradle Dependency
      dependencies {
            compile 'com.github.debutdeveloper:ViewPagerIndicator:v1.0'
      }
      
      
# How To Use in Java

First add kotlin support in your project.

[Add Kotlin support in Android Project](https://kotlinlang.org/docs/tutorials/kotlin-android.html)



# USAGE


```
        <com.debutinfotech.indicator.ViewPagerIndicator
                android:id="@+id/indicator"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginBottom="60dp"
                app:selectedColor="@color/colorAccent"
                app:deselectedColor="@color/colorPrimary"
                app:pageCount="4"
                app:indicatorSpacing="5dp"
                />
```
                
                
                
In your activity/fragment
```
         
         indicator.setpager(ViewPager)
                
```   

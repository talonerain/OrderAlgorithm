package com.lsy.orderalgorithm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int arr[];  //待排序数组
    private String TAG = "OrderAlgorithm";

    TextView tv_result;
    Button btn_insert;
    EditText edit_input;
    Button btn_select;
    Button btn_bubble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_insert = findViewById(R.id.btn_insert);
        tv_result = findViewById(R.id.tv_result);
        edit_input = findViewById(R.id.edit_input);
        btn_select = findViewById(R.id.btn_select);
        btn_bubble = findViewById(R.id.btn_bubble);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                insertSort();
                printResult();
            }
        });

        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                selectSort();
                printResult();
            }
        });
        btn_bubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                bubbleSort();
                printResult();
            }
        });
    }


    /*
    1.直接插入排序
    思路：类似扑克牌，假设第一个元素的位置是正确的，遍历数组，每次将当前的元素定位
    到正确位置上。具体方法是：将当前元素的值保存下来，依次向前遍历，大于
    这个值的元素向后移动，直到找到正确的位置，对其赋值。
    最好时间复杂度O(n)，最坏时间负责度O(n²)。
     */
    private void insertSort() {
        for(int i = 1; i < arr.length; i++) {
            int tempI = arr[i];
            int j = i;
            while (j > 0 && tempI < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = tempI;
        }
    }

    /*
    2. 选择排序：遍历，选出最小元素，与第一个元素交换位置，再从剩下元素中
    选出最小元素与第二个元素交换位置。
    最好时间复杂度O(n²)，最坏时间复杂度O(n²)
     */
    private void selectSort() {
        for(int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int position = i;
            for(int j = i + 1; j < arr.length; j++) {
                if (arr[j] < temp) {
                    temp = arr[j];
                    position = j;
                }
            }
            arr[position] = arr[i];
            arr[i] = temp;
        }
    }

    /*
    3. 冒泡排序：比较相邻的两个的元素，如果后面大于前一个则交换位置，这样
    一轮循环下来，最大的元素就被移到了最后一个位置。
    最好时间复杂度O(n)，最坏时间复杂度O(n²)
     */
    private void bubbleSort() {
        //外层循环控制排序趟数
        for (int i = 0; i < arr.length; i++) {
            //内层循环控制每趟排序多少次
            for(int j = 0; j < arr.length - 1 -i ; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    /*
    4. 快速排序：使用两个指针分别指向序列两端，取low值为中轴值，首先从
    high元素向前搜索到第一个比中轴值小的元素，将其放在low的位置，然后从low元素
    向后搜索到第一个比中轴值大的元素，将其放在high所指的位置。不断重复直至low
    和high相等，最后将中轴值设置到low和high共同所指的位置，这样一趟下来中轴值
    左边都是比其小的元素，右边都是比其大的元素，然后对两边递归这个过程。
    时间复杂度O(nlogn)
     */
    private void quickSort(int low, int high) {
        if (low < high) {
            int index = getMiddle(low, high);
            quickSort(low, index - 1);
            quickSort(index + 1, high);
        }
    }

    private int getMiddle(int low, int high) {
        int temp = arr[low];    //以第一个元素为中轴值
        while (low < high) {
            while (arr[high] > temp) {
                high--;
            }
            arr[low] = arr[high];   //找出比中轴值小的元素放在低端
            while (arr[low] < temp) {
                low++;
            }
            arr[high] = arr[low];   //找出比中轴值大的元素放在高端
        }
        arr[low] = temp; //此时low == high
        return low;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_input:
                break;
            case R.id.btn_insert:
                getData();
                insertSort();
                printResult();
                break;
        }
    }

    private void printResult() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++) {
            sb.append(" ");
            sb.append(arr[i]);
        }
        tv_result.setText(sb.toString());
    }

    private void getData() {
        String data = edit_input.getText().toString();
        if(!checkData(data))
            return;
        String strArr[] = data.split(",");
        arr = new int[strArr.length];
        for(int i = 0; i < strArr.length; i++) {
            arr[i] = Integer.valueOf(strArr[i]);
        }
    }

    private boolean checkData(String data) {
        return data != null && data.length() > 0;
    }

}

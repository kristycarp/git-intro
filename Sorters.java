//Kristy Carpenter, Computer Science III, String 2015, Section B (5th period)
//Assignment 13--Empirical
//
//This program contains the selection sort, insertion sort, and merge sort methods that are used in
//Empirical.java. It also contains code that tests each of these methods.

import java.util.*;

public class Sorters
{
   /**
     *this is the main method, where the program begins
     *
     *@param args
     */
   public static void main(String[] args)
   {
      //int[] array = createArray(10);
      //testSwap();
      testSelectionSort();
      testInsertionSort();
      //testMerge();
      testMergeSort();
      //kristy is cool 
   }
   
   /**
     *this method creates a new array of a given size made up of random elements, which are integers
     *between -100 and 100
     *
     *@param size - the size of the created array
     */
   public static int[] createArray(int size)
   {
      Random r = new Random();
      if (size > 0)
      {
         int[] array = new int[size];
         for (int ii = 0; ii < size; ii++)
         {
            array[ii] = r.nextInt(200) - 100;
         }
         return array;
      }
      else
      {
         throw new IllegalArgumentException();
      }
   }
   
   /**
     *this method swaps the elements at positions i and j in a given array
     *
     *@param list - the array in which things will be swapped
     *@param i - the position of the first thing to be swapped
     *@param j - the position of the second thing to be swapped
     */
   private static void swap(int[] list, int i, int j)
   {
      if (list == null)
      {
         throw new IllegalArgumentException();
      }
      else
      {
         if (i < 0 || i >= list.length || j < 0 || j >= list.length)
         {
            throw new IllegalArgumentException();
         }
         else
         {
            if (i != j)
            {
               int temp = list[i];
               list[i] = list[j];
               list[j] = temp;
            }
         }
      }
   }
   
   /**
     *this method tests the swap() method above
     *
     */
   public static void testSwap()
   {
      System.out.println("Testing swap...");
      try
      {
         System.out.println("test 1: passing in null list");
         swap(null, 1, 1);
      }
      catch (IllegalArgumentException e)
      {
         System.out.println("null list threw the correct exception");
      }
      
      int[] swapArray = {1, 2, 3, 4, 5};
      try
      {
         System.out.println("Test 2: Passing in illegal index...");
         swap(swapArray, 6, -1);
      }
      catch (IllegalArgumentException e)
      {
         System.out.println("illegal index threw correct exception");
      }
      System.out.println("Test 3: swap same indices");
      System.out.println("Before test 3 " + Arrays.toString(swapArray));
      swap(swapArray, 1, 1);
      System.out.println("After test 3 " + Arrays.toString(swapArray));
      
      System.out.println("Test 4: swap different indices");
      System.out.println("Before test 4 " + Arrays.toString(swapArray));
      swap(swapArray, 0, 1);
      System.out.println("After test 4 " + Arrays.toString(swapArray));
   }
   
   /**
     *this method performs a selection sort on the given array
     *
     *@param list - the array to be sorted
     */
   public static void selectionSort(int[] list)
   {
      for (int ii = 0; ii < list.length; ii++)
      {
         int smallest = ii;
         for (int jj = ii + 1; jj < list.length; jj++)
         {
            if (list[jj] < list[smallest])
            {
               smallest = jj;
            }
         }
         int temp = list[ii];
         list[ii] = list[smallest];
         list[smallest] = temp;
      }
   }
   
   /**
     *this method tests the selectionSort method above
     */
   public static void testSelectionSort()
   {
      System.out.println("testing selectionSort() method");
      int[] array = createArray(1);
      System.out.println(Arrays.toString(array));
      selectionSort(array);
      System.out.println(Arrays.toString(array));
   }
   
   /**
     *this method performs an insertion sort on the given array
     *
     *@param list - the array to be sorted
     */
   public static void insertionSort(int[] list)
   {
      int index = 0;
      int shift = 0;
      int tempValue = 0;
      for (index = 1; index < list.length; index++)
      {
         if (list[index] < list[index - 1])
         {
            tempValue = list[index];
            shift = index;
            do
            {
               list[shift] = list[shift - 1];
               shift--;
            } while (shift > 0 && list[shift - 1] > tempValue);
            list[shift] = tempValue;
         }
      }
   }
   
   /**
     *this method tests the insertionSort method above
     */
   public static void testInsertionSort()
   {
      System.out.println("testing insertionSort() method");
      int[] array = createArray(10);
      System.out.println(Arrays.toString(array));
      insertionSort(array);
      System.out.println(Arrays.toString(array));
   }
   
   /**
     *this method takes two sorted arrays and merges them into one big sorted array
     *
     *@param result - this array will contain the sorted contents of both of the other arrays at the end
     *@param list1 - the first sorted array to be merged
     *@param list2 - the second sorted array to be merged
     */
   private static void merge(int[] result, int[] list1, int[] list2)
   {
      if (result != null && list1 != null && list2 != null && list1.length + list2.length == result.length)
      {
         int i1 = 0;
         int i2 = 0;
         for (int ii = 0; ii < result.length; ii++)
         {
            if (i2 >= list2.length || (i1 < list1.length && list1[i1] < list2[i2]))
            {
               result[ii] = list1[i1];
               i1++;
            }
            else
            {
               result[ii] = list2[i2];
               i2++;
            }
         }
      }
      else
      {
         throw new IllegalArgumentException();
      }
   }
   
   /**
     *this method tests the merge method above
     */
   public static void testMerge()
   {
      System.out.println("testing merge() method");
      int[] result = new int[14];
            
      System.out.println("testing with null array input");
      try
      {
         merge(result, null, null);
      }
      catch (IllegalArgumentException e)
      {
         System.out.println("null input threw correct exception");
      }
      
      System.out.println("testing with bad result length");
      int[] array1 = createArray(10);
      insertionSort(array1);
      int[] array2 = createArray(10);
      insertionSort(array2);
      try
      {
         merge(result, array1, array2);
      }
      catch (IllegalArgumentException e)
      {
         System.out.println("bad result length threw correct exception");
      }
      
      System.out.println("testing without bad input:");
      int[] array3 = createArray(10);
      insertionSort(array3);
      System.out.println(Arrays.toString(array3));
      int[] array4 = createArray(4);
      insertionSort(array4);
      System.out.println(Arrays.toString(array4));
      merge(result, array3, array4);
      System.out.println(Arrays.toString(result));
   }
   
   /**
     *this method performs a merge sort on a given array
     *
     *@param list - the array to be sorted
     */
   public static void mergeSort(int[] list)
   {
      if (list.length > 1)
      {
         int size1 = list.length / 2;
         int size2 = list.length - size1;
         int[] list1 = new int[size1];
         int[] list2 = new int[size2];
         list1 = Arrays.copyOfRange(list, 0, size1);
         list2 = Arrays.copyOfRange(list, size1, list.length);
         mergeSort(list1);
         mergeSort(list2);
         merge(list, list1, list2);
      }
   }
   
   /**
     *this method tests the mergeSort method above
     */
   public static void testMergeSort()
   {
      System.out.println("testing mergeSort() method");
      int[] array = createArray(10);
      System.out.println(Arrays.toString(array));
      mergeSort(array);
      System.out.println(Arrays.toString(array));
   }

}
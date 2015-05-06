//Kristy Carpenter, Computer Science III, String 2015, Section B (5th period)
//Assignment 13--Empirical
//
//This program runs a selection sort, insertion sort, and merge sort on arrays of varying sizes in
//different initial orders. It records the amount of time needed for each of these sorts, and reports
//all these times in a nicely formatted table.
//This program calls multiple methods from Sorters.java

import java.util.*; 


public class Empirical
{
   /**
     *this enum contains the different types of sorts to be tested in this program
     */
   private enum Sort {selectionSort, insertionSort, mergeSort}
   
   /**
     *this enum contains the different ways in which the arrays will be created before their sorting
     */
   private enum ArrayType {Increasing, Decreasing, Random}
   
   /**
     *this array contains the different array lengths to be tested
     */
   private static final int[] ARRAY_SIZE = {1000, 5000, 10000, 20000, 50000};
   
   /**
     *this class constant is the largest array size to be tested
     */
   private static final int MAX_ARRAY_SIZE = 50000;
   
   /**
     *this is the main method, where the program begins
     *
     *@param args
     */
   public static void main(String[] args)
   {
      int[] times = new int[45]; //number of sorts * number of array orders * number of array sizes
      int slot = 0;
      for (ArrayType t : ArrayType.values())
      {
         int[] array = new int[MAX_ARRAY_SIZE];
         if (t.equals(ArrayType.Increasing))
         {
            for (int ii = 0; ii < MAX_ARRAY_SIZE; ii++)
            {
               array[ii] = ii;
            }
         }
         else if (t.equals(ArrayType.Decreasing))
         {
            for (int ii = 0; ii < MAX_ARRAY_SIZE; ii++)
            {
               array[ii] = MAX_ARRAY_SIZE - ii;
            }
         }
         else
         {
            array = Sorters.createArray(MAX_ARRAY_SIZE);
         }
         for (Sort sort : Sort.values())
         {
            for (int size : ARRAY_SIZE)
            {
               times[slot] = runSort(sort, size, array);
               slot++;
            }
         }
      }
      formatTable(times);

   }
   
   /**
     *this method sorts an array with a given sort, then returns the time it took to do that sort
     *
     *@param sortType - the sort to be used
     *@param size - the size of the array to be sorted
     *@param largeArray - the full version of the array to be sorted; only part of it might end up being used
     *
     *@return the amount of time taken to sort the array
     */
   private static int runSort(Sort sortType, int size, int[] largeArray)
   {
      int[] array = Arrays.copyOf(largeArray, size);
      System.gc();
      long beginTime = System.currentTimeMillis();
      if (sortType.equals(Sort.selectionSort))
      {
         Sorters.selectionSort(array);
      }
      else if (sortType.equals(Sort.insertionSort))
      {
         Sorters.insertionSort(array);
      }
      else
      {
         Sorters.mergeSort(array);
      }
      long endTime = System.currentTimeMillis();
      return (int) (endTime - beginTime);
   }
   
   /**
     *this method prints out a formatted table with the data of how long it took to perform each sort
     *
     *@param times - the array of runtimes for each sort
     */
   private static void formatTable(int[] times)
   {
      int counter = 0;
      for (ArrayType t : ArrayType.values())
      {
         System.out.printf("%15s", t);
         for (int i : ARRAY_SIZE)
         {
            System.out.printf("\t%d", i);
         }
         System.out.println("");
         for (Sort s : Sort.values())
         {
            System.out.printf("%15s", s);
            for (int i : ARRAY_SIZE)
            {
               System.out.printf("\t%d", times[counter]);
               counter++;
            }
            System.out.println("");
         }
         System.out.println("");
      } 
   }
}
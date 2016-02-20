import static org.junit.Assert.*;
import org.junit.*;

import java.util.Arrays;

public class MatrixTest{
  @Test
  public void initShouldInitializeTheMatrix() {
   Matrix matrix = new Matrix(3,3);
   int [] elements = {2,3,4,5,6,7,8,9,1};
   matrix.init(elements);
   assertEquals(matrix,matrix);
  }
  @Test
  public void addShouldAddTheMatricesAndReturnNewMatrix() {
   int [] expectedResult = {5,7,10,6,13,11,17,14,7};
   Matrix expected =new Matrix(3,3);
   expected.init(expectedResult);

   Matrix matrix1 = new Matrix(3,3);
   int [] elements1 = {2,3,4,5,6,7,8,9,1};
   matrix1.init(elements1);

   Matrix matrix2 = new Matrix(3,3);
   int [] elements2 = {3,4,6,1,7,4,9,5,6};
   matrix2.init(elements2);

   Matrix actual= matrix1.add(matrix2);
   assertEquals(expected,actual);
  }
  @Test
  public void addShouldReturnNullMatrixIfTheyCanNotBeAdded() {
   Matrix expected = new Matrix(0,0);

   Matrix matrix1 = new Matrix(2,3);
   int [] elements1 = {2,3,4,5,6,7};
   matrix1.init(elements1);

   Matrix matrix2 = new Matrix(3,2);
   int [] elements2 = {3,4,6,1,7,4};
   matrix2.init(elements2);

   Matrix addition= matrix1.add(matrix2);
   assertEquals(expected,addition);
  }
  @Test
  public void multiplyShouldMultiplyTheMatricesAndReturnNewMatrix() {
   int [] expectedResult = {23,36,41,66};
   Matrix expected =new Matrix(2,2);
   expected.init(expectedResult);

   Matrix matrix1 = new Matrix(2,2);
   int [] elements1 = {2,3,4,5};
   matrix1.init(elements1);

   Matrix matrix2 = new Matrix(2,2);
   int [] elements2 = {4,9,5,6};
   matrix2.init(elements2);

   Matrix actual= matrix1.multiply(matrix2);
   assertEquals(expected,actual);
  }
  @Test
  public void multiplyShouldMultiplyTheMatricesAndReturnNewMatrixOfDifferentOrders() {
   int [] expectedResult = {6,27,5,22};
   Matrix expected =new Matrix(2,2);
   expected.init(expectedResult);

   Matrix matrix1 = new Matrix(2,3);
   int [] elements1 = {2,1,4,5,2,1};
   matrix1.init(elements1);

   Matrix matrix2 = new Matrix(3,2);
   int [] elements2 = {0,3,2,1,1,5};
   matrix2.init(elements2);
   Matrix actual = matrix1.multiply(matrix2);
   assertEquals(expected,actual);
  }
  @Test
  public void multiplyShouldReturnNullMatrixIfTheyCanNotBeMultiplied() {
   Matrix expected =new Matrix(0,0);

   Matrix matrix1 = new Matrix(2,1);
   int [] elements1 = {2,3};
   matrix1.init(elements1);

   Matrix matrix2 = new Matrix(2,4);
   int [] elements2 = {4,9,5,6,3,6,8,2};
   matrix2.init(elements2);

   Matrix actual= matrix1.multiply(matrix2);
   assertEquals(expected,actual);
  }
  @Test
  public void getDeterminantShouldReturnInfiniteWhenNotPossible(){
    Matrix matrix = new Matrix(2,1);
    int [] elements = {2,3,2,5};
    matrix.init(elements);
    assertTrue(Double.isInfinite(matrix.getDeterminant()));
  }
  @Test
  public void getDeterminantShouldReturnAnRepresentationOfMatrix(){
    Matrix matrix = new Matrix(2,2);
    int [] elements = {2,3,2,5};
    matrix.init(elements);
    assertEquals(4,matrix.getDeterminant(),0.0);
  }
  @Test
  public void getDeterminantShouldReturnAnRepresentationOf3rdOrderMatrix(){
    Matrix matrix = new Matrix(3,3);
    int [] elements = {2,3,2,5,4,6,1,6,0};
    matrix.init(elements);
    assertEquals(-2,matrix.getDeterminant(),0.0);
  }
  @Test
  public void getDeterminantShouldReturnAnRepresentationOf4thOrderMatrix(){
    Matrix matrix = new Matrix(4,4);
    int [] elements = {3,0,2,-1,1,2,0,-2,4,0,6,-3,5,0,2,0};
    matrix.init(elements);
    assertEquals(20,matrix.getDeterminant(),0.0);
  }
  @Test
  public void getDeterminantShouldReturnAnRepresentationOf5thOrderMatrix(){
    Matrix matrix = new Matrix(5,5);
    int [] elements = {1,3,2,5,1,3,9,2,1,4,4,2,5,6,2,2,1,3,1,2,5,6,2,1,3};
    matrix.init(elements);
    assertEquals(93,matrix.getDeterminant(),0.0);
  }
}
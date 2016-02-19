import java.util.Arrays;

public class Matrix{
  private int[][] elements;
  private int row;
  private int col;
  public Matrix(int row ,int col){
      this.row = row;
      this.col = col;
      this.elements = new int[row][col];
  }
  @Override
  public String toString(){
    String output="";
    for (int i=0;i<this.row;i++) {
      for (int j=0;j<this.col;j++) {
        output = output+ this.elements[i][j]+" ";
      }
      output = output +" \n";
    }
    return output;
  }
  public void init(int[] elements){
    int k = 0;
    for (int i=0;i<this.row;i++) {
      for (int j=0;j<this.col && k < elements.length;j++) {
        this.elements[i][j] = elements[k++];
      }
    }
  }
  private boolean isSameSize(Matrix matrix){
    return (matrix.row ==this.row && matrix.col ==this.col);
  }
  public Matrix add(Matrix addended) {
    Matrix resultMatrix = new Matrix(row,col);
    int[] result = new int[row*col];
    if(!isSameSize(addended))
      return new Matrix(0,0);
    int k = 0;
    for (int i=0;i<this.row;i++) {
      for (int j=0;j<this.col ;j++) {
        result[k++]=this.elements[i][j]+addended.elements[i][j];
      }
    }
    resultMatrix.init(result);
    return resultMatrix;
  }
  public boolean isEqual(Matrix matrix){
    return Arrays.deepEquals(matrix.elements,this.elements);
  }
  private boolean isSquareMatrix(){
    return (row==col);
  }
  private int twoOrder(){
    int first = elements[0][0]*elements[1][1];
    int second = elements[1][0]*elements[0][1];
    return first-second;
  }
  public double getDeterminant(){
    if(!isSquareMatrix())
        return Double.POSITIVE_INFINITY;
    return (double)twoOrder();
  }
  private boolean canBeMultipliedWith(Matrix matrix){
    return (this.col == matrix.row);
  }
  public Matrix multiply(Matrix matrix){
    Matrix resultMatrix = new Matrix(this.row,matrix.col);
    int[] result = new int[this.row*matrix.col];
    if(!canBeMultipliedWith(matrix))
      return new Matrix(0,0);
    int l =0;
    for (int i=0;i< this.row;i++){
      for (int j=0;j< matrix.col ;j++) {
          int sum = 0;
        for (int k = 0; k< matrix.row;k++){
          sum=this.elements[i][k]*matrix.elements[k][j]+sum;
        }
          result[l++] = sum;
      }
    }
    resultMatrix.init(result);
    return resultMatrix;
  }
}

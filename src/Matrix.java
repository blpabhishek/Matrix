import static java.util.Arrays.deepEquals;

public class Matrix{
  private int[][] elements;
  private final int row;
  private final int col;
  public Matrix(int row ,int col){
      this.row = row;
      this.col = col;
      this.elements = new int[row][col];
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
  private boolean equals(Matrix matrix){
    return deepEquals(matrix.elements,this.elements);
  }
  private boolean isSquareMatrix(){
    return (row==col);
  }
  private int determinantOfSecondOrder(){
    int first = elements[0][0]*elements[1][1];
    int second = elements[1][0]*elements[0][1];
    return first-second;
  }
  private boolean canBeMultipliedWith(Matrix matrix){
    return (this.col == matrix.row);
  }
  public Matrix add(Matrix addended) {
    Matrix resultMatrix = new Matrix(row,col);
    int[] result = new int[row*col];
    if(!isSameSize(addended))
      return new Matrix(0,0);
    int k = 0;
    for (int i=0;i<this.row;i++) {
      for (int j=0;j<this.col;j++) {
        result[k++]=this.elements[i][j]+addended.elements[i][j];
      }
    }
    resultMatrix.init(result);
    return resultMatrix;
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
  private boolean isSecondOrder(Matrix matrix){
    return (matrix.col ==2 || matrix.row ==2);
  }
  public double getDeterminant(){
    if(!isSquareMatrix())
        return Double.POSITIVE_INFINITY;
    if(isSecondOrder(this))
      return (double)determinantOfSecondOrder();
    return (double)calculateDeterminant(this);
  }
  private int calculateDeterminant(Matrix matrix){
    int result = 0;
    for (int i=0;i<matrix.col;i++) {
      int temp = matrix.elements[0][i];
      if(i%2==1)  temp*=-1;
      Matrix cofactor = getCofactor(matrix,0,i);
      if(isSecondOrder(cofactor)){
        int answer = cofactor.determinantOfSecondOrder();
        temp*=answer;
      }
      result+=temp;
    }
    return result;
  }
  private Matrix getCofactor(Matrix matrix,int rowSkip,int colSkip){
    Matrix cofacor = new Matrix(matrix.row-1,matrix.col-1);
    int []elements = new int[(matrix.row-1)*(matrix.col-1)];
    int k = 0;
    for (int i =0 ;i < matrix.row;i++) {
        for (int j=0;j<matrix.col;j++) {
              if( i!=rowSkip && j!=colSkip )
                elements[k++]=matrix.elements[i][j];
           }   
    }
    cofacor.init(elements);
    return cofacor;
  };
  @Override
  public String toString(){
    String output="";
    for (int i=0;i<this.row;i++) {
      for (int j=0;j<this.col;j++) {
        output += this.elements[i][j]+" ";
      }
      output +="\n";
    }
    return output;
  }
  @Override
  public boolean equals(Object matrix){
    if(this == matrix) return true;
    if(!(matrix instanceof Matrix)) return false;
    return equals((Matrix)matrix);
  }
}


public class ImageBuilder {
    Boolean[][] image; //Boolean vector keeping track oh which points are on.
    float sizeFactorX;
    float sizeFactorY;

    public static void main(String[] args) {

    }
    public ImageBuilder(int maxCoordX, int maxCoordY, int maxPointsX, int maxPointsY) {
        image = new Boolean[maxPointsX][maxPointsY];
        for (int i = 0; i < maxPointsX; i++) {
            for (int j = 0; j < maxPointsY; j++) {
                image[i][j] = false;
            }
        }
        sizeFactorX = (float)maxPointsX/(float)maxCoordX;
        sizeFactorY = (float)maxPointsY/(float)maxCoordY;
    }

    /**
     * Adding a coordinate to the array representing the image.
     * @param x X coordinate of the point (in the original scale of the drawing window)
     * @param y Y coordinate of the point (...)
     */
    public void setPoint(int x, int y) {
        int convertedX = convertCoord(x, sizeFactorX);
        int convertedY = convertCoord(y, sizeFactorY);
        if( convertedX<0 || convertedX>=image.length || convertedY<0 || convertedY>=image[0].length){
            System.out.println("Out of bounds");
            return;
        }
        image[convertedX][convertedY] = true;
        System.out.println("Setting point (" + convertedX + ", " + convertedY + ")");
    }
    public Boolean[][] getImage() {
        return image;
    }

    /**
     * This function saves the array representing the image as a c language header "pointValues.h" to be
     * included with the arduino code available in the repo.
     */
    public void saveImage(){
        String outputString = "";
        outputString += "typedef uint8_t fourBits;"+'\n' + "const fourBits xValues[] = {" +'\n';
        //Add x coordinates
        for (int x = 0; x < image.length; x++) {
            for (int y = 0; y < image[1].length; y++) {
                if(image[x][y]){
                    outputString += '\t'+"0x"+convertToHex(x)+','+'\n';
                }
            }
        }
        //Closing x coords vector
        outputString+="\t};\n";
        //Starting y coords vector
        outputString += "const fourBits yValues[] = {\n";
        for (int x = 0; x < image.length; x++) {
            for (int y = 0; y < image[1].length; y++) {
                if(image[x][y]){
                    outputString += '\t'+"0x"+convertToHex(y)+','+'\n';
                }
            }
        }
        //Closing y coords vector
        outputString+="\t};\n";
        //Add image size definition:
        outputString += "\n\n#define IMAGE_SIZE (sizeof(xValues) / sizeof(xValues[0]))";
        FileWriter.writeToFile("pointValues.h", outputString);
    }
    protected static int convertCoord(int coord, float sizeFactor) {
        return (int)(Math.round(coord*sizeFactor));
    }
    protected static String convertToHex(int coord){
        return(Integer.toHexString(coord).toUpperCase());
    }
}

# XYOscilloscopeDrawWithDAC
I made this project to experiment with R-2R digital to analog converters and create retro visuals on analog oscilloscopes in X/Y mode.<br>
The projects consists of a java program available in the src folder that allows the user to draw a shape. When the window is closed, a c header named pointValues.h is created with the data of the drawn image.<br>
![](https://github.com/zpell057/XYOscilloscopeDrawWithDAC/blob/main/softwareDemo.gif)
<br>
The user then puts that header with the arduino file to upload them to an arduino uno connected to two R-2R DACs that are connected to the nodes of an oscilloscope in X/Y mode.<br>
![](https://github.com/zpell057/XYOscilloscopeDrawWithDAC/blob/main/videoDemo.gif)

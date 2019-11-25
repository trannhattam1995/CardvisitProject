package com.example.eternity.cardvisitproject

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View



/**
 * TODO: document your custom view class.
 */
class DrawView : View {

    var paint : Paint = Paint()
    var path : Path = Path()
    var color : Int = Color.BLACK
    var size : Float = 0f
    var tool_id : Int = 0
    var shape_ID : Shape_ID = Shape_ID.NONE_SHAPE


    var listShape : ArrayList<Shape> = ArrayList<Shape>()

    var s_x : Float = 0f
    var s_y : Float = 0f
    var e_x : Float = 0f
    var e_y : Float = 0f

    var creating_circle :Circle? = null

    var isDropMove : Boolean = false
    var drop_flag : Boolean = false
    var selected_shape_id : Int = 0
    var point_x : Float = 0f
    var point_y : Float = 0f
    var bitmap :Bitmap? = null

    var saveBitmap : Bitmap? = null

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        path = Path()

        paint = Paint()
        paint.color = -0xff7800
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.setStrokeWidth(10f)
        ///paint.isAntiAlias = true

    }



    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


//        //バックグラウンドを描く処理
//        bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.background) , DrawView.width,DrawView.height,false)
//        canvas.drawBitmap(bitmap,0f,0f,paint)
        //paint を描く処理
        canvas.drawPath(path,paint)

//        canvas.drawLine(10f,100f,10f,200f, paint)
        //図形を描く処理
        for(shape in listShape){
            shape.draw(canvas)
        }

    }

    //新しい円図形を描く
    fun DrawCircle(x : Float , y :Float ,e_x : Float ,e_y: Float ,  paint : Paint){
        var center_x : Float = s_x + ( e_x - s_x) / 2
        var center_y : Float = s_y + ( e_y - s_y) / 2
        var r : Float = Math.abs( center_x - s_x) * Math.abs( center_x - s_x) + Math.abs( center_y - s_y) * Math.abs( center_y - s_y)
        r = Math.sqrt(r.toDouble()).toFloat()
        var cirle = Circle(x,y,r,paint)
        listShape.add(cirle)
        invalidate()
    }
    //円を変更
    fun UpdateCircle( circle: Circle , x : Float , y :Float ,e_x : Float , e_y : Float ,  paint : Paint){
        var center_x : Float = s_x + ( e_x - s_x) / 2
        var center_y : Float = s_y + ( e_y - s_y) / 2
        var r : Float = Math.abs( center_x - s_x) * Math.abs( center_x - s_x) + Math.abs( center_y - s_y) * Math.abs( center_y - s_y)
        r = Math.sqrt(r.toDouble()).toFloat()
        circle.update(x,y,r,paint)
        invalidate()
    }


    //新しい四角形を描く
    fun DrawQuare( x : Float , y :Float ,e_x : Float , e_y : Float ,  paint : Paint){
        var quare : Quare = Quare(x,y,e_x ,e_y , paint)
        listShape.add(quare)
        invalidate()
    }

    //四角更新
    fun UpdateQuare( quare : Quare , x : Float , y :Float ,e_x : Float , e_y : Float ,  paint : Paint){
        quare.update(x,y,e_x,e_y,paint)
        invalidate()
    }

    //新しい線を引く
    fun DrawLine(  x : Float , y :Float ,e_x : Float , e_y : Float ,  paint : Paint){
        var line : Line = Line(x,y,e_x ,e_y , paint)
        listShape.add(line)
        invalidate()
    }

    //新しいOval形を描く
    fun DrawOval( x : Float , y :Float ,e_x : Float , e_y : Float ,  paint : Paint){
        var oval : Oval = Oval(x,y,e_x ,e_y , paint)
        listShape.add(oval)
        invalidate()
    }

    //Oval形角更新
    fun UpdateOval( oval : Oval , x : Float , y :Float ,e_x : Float , e_y : Float ,  paint : Paint){
        oval.update(x,y,e_x,e_y,paint)
        invalidate()
    }


    //新しい線を引く
    fun UpdateLine( line : Line , x : Float , y :Float ,e_x : Float , e_y : Float ,  paint : Paint){
        line.update(x,y,e_x,e_y,paint)
        invalidate()
    }

    //線を変更
    fun UpdateQuare( line: Line  , x : Float , y :Float ,e_x : Float , e_y : Float ,  paint : Paint){
        line.update(x,y,e_x,e_y,paint)
        invalidate()
    }

    //最後図形を削除
    fun RemoveLastShape(){
        listShape.removeAt(listShape.count() - 1)
        invalidate()
    }


    //ペイントのカラー
    fun setPaintColor(color : Int){
        paint.color = color
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var action : Int = event!!.action

        when( action) {
            MotionEvent.ACTION_DOWN->{
                when(shape_ID){
                    Shape_ID.NONE_SHAPE->{
                        for( i in 0..listShape.count() - 1){
                            if( listShape[i].isOnTouch( event.x, event.y)){
                                drop_flag = true
                                selected_shape_id = i
                                point_x = event.x
                                point_y = event.y
                            }
                        }

                    }
                    Shape_ID.SHAPE_CIRCLE->{
                        s_x = event.x
                        s_y = event.y
                    }
                    Shape_ID.SHAPE_PAINT->{
                        path.moveTo(event.x,event.y)
                        invalidate()
                    }
                    Shape_ID.SHAPE_QUARE->{
                        s_x = event.x
                        s_y = event.y
                    }
                    Shape_ID.SHAPE_OVAL->{
                        s_x = event.x
                        s_y = event.y
                    }
                    Shape_ID.SHAPE_LINE->{
                        s_x = event.x
                        s_y = event.y
                    }


                }

            }
            MotionEvent.ACTION_MOVE->{
                when(shape_ID){
                    Shape_ID.NONE_SHAPE ->{
                        if( drop_flag){
                            var move_distan_x = event.x - point_x
                            var move_distan_y = event.y - point_y
                            listShape[selected_shape_id].move(listShape[selected_shape_id].x + move_distan_x , listShape[selected_shape_id].y + move_distan_y)
                            point_x = event.x
                            point_y = event.y
                            invalidate()

                        }
                    }
                    Shape_ID.SHAPE_CIRCLE ->{
                        e_x = event.x
                        e_y = event.y
                        if( isDropMove == false){
                            DrawCircle(s_x,s_y,s_x,s_y,paint)
                            isDropMove = true
                        }else{
                            var circle = listShape[listShape.size-1] as Circle
                            UpdateCircle( circle  , s_x,s_y,e_x,e_y,paint)
                        }

                    }
                    Shape_ID.SHAPE_PAINT ->{
                        path.lineTo(event.x,event.y)
                        invalidate()
                    }
                    Shape_ID.SHAPE_QUARE->{
                        e_x = event.x
                        e_y = event.y
                        if( isDropMove == false){
                            DrawQuare(s_x,s_y,s_x,s_y,paint)
                            isDropMove = true
                        }else{
                            var quare = listShape[listShape.size-1] as Quare
                            UpdateQuare(quare  , s_x,s_y,e_x,e_y,paint)
                        }

                    }
                    Shape_ID.SHAPE_OVAL->{
                        e_x = event.x
                        e_y = event.y
                        if( isDropMove == false){
                            DrawOval(s_x,s_y,s_x,s_y,paint)
                            isDropMove = true
                        }else{
                            var oval = listShape[listShape.size-1] as Oval
                            UpdateOval(oval , s_x,s_y,e_x,e_y,paint)
                        }
                    }
                    Shape_ID.SHAPE_LINE->{
                        e_x = event.x
                        e_y = event.y
                        if( isDropMove == false){
                            DrawLine(s_x,s_y,s_x,s_y,paint)
                            isDropMove = true
                        }else{
                            var line = listShape[listShape.size-1] as Line
                            UpdateLine(line,s_x,s_y,e_x,e_y,paint)
                        }

                    }

                }
            }
            MotionEvent.ACTION_UP->{
                when(shape_ID){
                    Shape_ID.NONE_SHAPE ->{
                        if( drop_flag){
                            var move_distan_x = event.x - point_x
                            var move_distan_y = event.y - point_y
                            listShape[selected_shape_id].move(listShape[selected_shape_id].x + move_distan_x , listShape[selected_shape_id].y + move_distan_y)
                            invalidate()
                            drop_flag = false
                        }

                    }
                    Shape_ID.SHAPE_CIRCLE ->{
                        if( isDropMove){
                            e_x = event.x
                            e_y = event.y
                            var circle = listShape[listShape.size-1] as Circle
                            UpdateCircle( circle  , s_x,s_y,e_x,e_y,paint)
                            isDropMove = false
                        }
                    }
                    Shape_ID.SHAPE_PAINT ->{
                        path.lineTo(event.x,event.y)
                        invalidate()
                    }
                    Shape_ID.SHAPE_QUARE->{
                        if( isDropMove){
                            e_x = event.x
                            e_y = event.y
                            var quare = listShape[listShape.size-1] as Quare
                            UpdateQuare(quare  , s_x,s_y,e_x,e_y,paint)
                            isDropMove = false
                        }
                    }
                    Shape_ID.SHAPE_OVAL->{
                        if( isDropMove){
                            e_x = event.x
                            e_y = event.y
                            var oval = listShape[listShape.size-1] as Oval
                            UpdateOval(oval  , s_x,s_y,e_x,e_y,paint)
                            isDropMove = false
                        }
                    }
                    Shape_ID.SHAPE_LINE->{
                        if( isDropMove){
                            e_x = event.x
                            e_y = event.y
                            var line = listShape[listShape.size-1] as Line
                            UpdateLine(line,s_x,s_y,e_x,e_y,paint)
                            isDropMove = false
                        }
                    }

                }
            }
        }
        return true
    }



}

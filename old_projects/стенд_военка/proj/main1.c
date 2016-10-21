#include <ioavr.h>
#include <intrinsics.h>
#include "keyboard.h"

unsigned char number[] =
{
  0x3f, //0
  0x06, //1
  0x5b, //2
  0x4f, //3  
  0x66, //4
  0x6d, //5
  0x7d, //6
  0x07, //7  
  0x7f, //8
  0x6f  //9   
};

//числа для вывода на индикатор
unsigned char data1 = 0;
unsigned char data2 = 0;
unsigned char buf = 0;

int indicate(char digit, char indN){
	switch (indN)
	{
	case '1' : 
		{
			PORTX_C |= (1<<0);
			DDRX_C |= (1<<0);
			PORTX_SEG = number[digit];
		}
		break;
	case '2' : 
		{
			PORTX_C |= (1<<1);
			DDRX_C |= (1<<1);
			PORTX_SEG = number[digit];
		}
		break;
	case '3' : 
		{
			PORTX_C |= (1<<3);
			DDRX_C |= (1<<3);
			PORTX_SEG = number[digit];
		}
		break;
		default: PORTX_C &= ~((1<<2)| (1<<1)| (1<<0));
	}
	
}

int main( void )
{


  //порт, к которому подкл. сегменты
  PORTB = 0xff;
  DDRB = 0xff;
 
  //порт, к которому подкл. катод
  PORTC = 0;
  DDRC |= (1<<1)|(1<<0);
  KEYB_Init();                  //инициализация
  
  int count = 0;
  while(1){
     __delay_cycles(1000);
     KEYB_ScanKeyboard();          //сканирование клавиатуры
     buf = KEYB_GetKey();       //взять код нажатой кнопки  
     if(buf){
       count++;
       if(count == 4){}
       
       
              
     }
  }
}  




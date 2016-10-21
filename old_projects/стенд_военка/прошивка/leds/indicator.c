#include <intrinsics.h>
#include "indicator.h"

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

volatile  unsigned char data[3];
volatile  unsigned char LEDS[];

void IND_Init(void)
{
   //порт, к которому подкл. сегменты
   PORT_IND = 0xff;
   DDR_IND = 0xff;

   //порт, к которому подкл. катод
   PORT_K &= ~((1<<KAT3)|(1<<KAT2)|(1<<KAT1));
   DDR_K |= (1<<KAT3)|(1<<KAT2)|(1<<KAT1); 
  
   PORT_PLUS = 0xff;
   DDR_PLUS = 0xff;
  
   PORT_BASE &= ~((1<<BASE5)|(1<<BASE4)|(1<<BASE3)|(1<<BASE2)|(1<<BASE1));
   DDR_BASE |= ((1<<BASE5)|(1<<BASE4)|(1<<BASE3)|(1<<BASE2)|(1<<BASE1));

   data[0] = 0;
   data[1] = 0;
   data[2] = 0;
}

void ioinit(void){
    DDRA = 0x3f;
    PORTA  = 0x00;
}

void output_led_state(unsigned char __led_state_col, unsigned char __led_state_row)
{
   SH_CP_row_low();
   ST_CP_row_low();

   SH_CP_col_low();
   ST_CP_col_low();
   
   for(int i = 0; i < 16; i++){
       if(bit_is_set(__led_state_row, i))
	{
           DS_row_high();
           DS_row_low();
	}
        else  
           DS_row_low();
      SH_CP_row_high();
      SH_CP_row_low();
   
       if(bit_is_set(__led_state_col, i))
       {
	   DS_col_high();
           DS_col_low();
	}
        else  
           DS_col_low();

      SH_CP_col_high();
      SH_CP_col_low();
   }
   ST_CP_col_high();
   ST_CP_row_high();   
}


void IND_Conv(unsigned int value)
{
  //конвертация цифр для индикатора
  unsigned char tmp;
  tmp = value % 10;
  data[0] =  number[tmp];
  tmp = value / 10 % 10;
  data[1] =  number[tmp];
  tmp = value / 100;
  data[2] =  number[tmp];
  
  //конфертация номера в координаты матрицы светодиодов
  unsigned int x;
  unsigned int y;
  x = value / 16;
  y = value % 16;
  output_led_state(_BV(x),_BV(y));
  
}

void IND_Update(void)
{
  // обновление индикатора
  static unsigned char count = 0;

   PORT_K &= ~((1<<KAT3)|(1<<KAT2)|(1<<KAT1));
   PORT_IND = data[count];   
   
   if (count == 0) PORT_K |= (1<<KAT1);
   if (count == 1) PORT_K |= (1<<KAT2);
   if (count == 2) PORT_K |= (1<<KAT3);   

   count++;
   if (count == 3) count = 0;
}

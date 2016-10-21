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

volatile  unsigned char data[2];

void IND_Init(void)
{
  //порт, к которому подкл. сегменты
  PORT_IND = 0xff;
  DDR_IND = 0xff;
  
  //порт, к которому подкл. катод
  PORT_K &= ~((1<<KAT2)|(1<<KAT1));
  DDR_K |= (1<<KAT2)|(1<<KAT1); 

   data[0] = 0;
   data[1] = 0;
}

void IND_Conv(unsigned char value)
{
  unsigned char tmp;
  tmp = value % 10;
  data[0] =  number[tmp];
  tmp = value/10;
  data[1] =  number[tmp];
}

void IND_Update(void)
{
   static unsigned char count = 0;

   PORT_K &= ~((1<<KAT2)|(1<<KAT1));
   PORT_IND = data[count];   
   
   if (count == 0) PORT_K |= (1<<KAT1);
   if (count == 1) PORT_K |= (1<<KAT2);
      
    count++;
    if (count == 2) count = 0;
}
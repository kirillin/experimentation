//***************************************************************************
//
//  Author(s)...: Pashgan    http://ChipEnable.Ru   
//
//  Target(s)...: ATMega8
//
//  Compiler....: IAR 5.11
//
//  Description.: Эксперименты с семисегментным индикатором
//
//  Data........: 15.04.10 
//
//***************************************************************************
#include <ioavr.h>
#include <intrinsics.h>
#include "indicator.h"

#define TIMER_CONST 0xb2

//программный счетчик секунд
unsigned char counterProg = 0;

int main( void )
{
  IND_Init();
  
  //инициализация таймера Т0 
  TIMSK = (1<<TOIE0);
  TCCR0 = (1<<CS02)|(0<<CS01)|(1<<CS00);
  TCNT0 =  TIMER_CONST;
    
  __enable_interrupt();
  while(1){
    //программный счетчик секунд
    counterProg++;
    if (counterProg == 100) counterProg = 0;
    IND_Conv(counterProg);
    __delay_cycles(8000000);    
  }  
  return 0;
}

//прерывания таймера Т0 - вывод на индикатор
#pragma vector = TIMER0_OVF_vect
__interrupt void Timer0_Ovf(void)
{
  TCNT0 = TIMER_CONST;
  IND_Update();
}
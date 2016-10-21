//***************************************************************************
//
//  Author(s)...: Pashgan    http://ChipEnable.Ru   
//
//  Target(s)...: ATMega8
//
//  Compiler....: IAR 5.11
//
//  Description.: ������������ � �������������� �����������
//
//  Data........: 15.04.10 
//
//***************************************************************************
#include <ioavr.h>
#include <intrinsics.h>
#include "indicator.h"

#define TIMER_CONST 0xb2

//����������� ������� ������
unsigned char counterProg = 0;

int main( void )
{
  IND_Init();
  
  //������������� ������� �0 
  TIMSK = (1<<TOIE0);
  TCCR0 = (1<<CS02)|(0<<CS01)|(1<<CS00);
  TCNT0 =  TIMER_CONST;
    
  __enable_interrupt();
  while(1){
    //����������� ������� ������
    counterProg++;
    if (counterProg == 100) counterProg = 0;
    IND_Conv(counterProg);
    __delay_cycles(8000000);    
  }  
  return 0;
}

//���������� ������� �0 - ����� �� ���������
#pragma vector = TIMER0_OVF_vect
__interrupt void Timer0_Ovf(void)
{
  TCNT0 = TIMER_CONST;
  IND_Update();
}
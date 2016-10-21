//***************************************************************************
//
//  Author(s)...: Pashgan    http://ChipEnable.Ru   
//
//  Target(s)...: ATMega8
//
//  Compiler....: IAR 5.11A
//
//  Description.: ����� ��������� ����������. ������������� ��������� ��������.
//
//  Data........: 06.03.10 
//
//***************************************************************************
#include <ioavr.h>
#include <intrinsics.h>
#include "keyboard.h"

//�������� ������� �� usart`�
void USART_SendChar(unsigned char sym)
{
  while(!(UCSRA & (1<<UDRE)));
  UDR = sym;
}

//���������� ������� �0 - ����� ����������
#pragma vector = TIMER0_OVF_vect
__interrupt void Timer0Ovf(void)
{
   TCNT0 = 0x83;
   ScanKeyboard();   
}

int main( void )
{
  unsigned char key;
  
  //������������� USART`a
  UBRRH = 0;
  UBRRL = 51; //�������� ������ 9600 ��� ��� Fcpu = 8M��
  UCSRB = (1<<TXEN); // ���� ��������.
  UCSRC = (1<<URSEL)|(1<<UCSZ1)|(1<<UCSZ0); //������ ����� 8 ��������
  
  //������������� ������� �0
  TIMSK = (1<<TOIE0); //���������� ���������� �� ������������
  TCCR0 = (1<<CS02)|(0<<CS01)|(0<<CS00); //������������ 256
  TCNT0 = 0x83; //���������� ������ 4 ��
  
  //������������� ������ � ���������� 
  InitKeyboard();
  __enable_interrupt();
  
  while(1){
    //���� ������������� �������, 
    //��������� ��� ������ � ��������
    key = GetKey();
    if (key)
      USART_SendChar(key);
  }
  return 0;
}


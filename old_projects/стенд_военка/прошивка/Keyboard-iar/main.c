//***************************************************************************
//
//  Author(s)...: Pashgan    http://ChipEnable.Ru   
//
//  Target(s)...: ATMega8
//
//  Compiler....: IAR 5.11A
//
//  Description.: Опрос матричной клавиатуры. Использование конечного автомата.
//
//  Data........: 06.03.10 
//
//***************************************************************************
#include <ioavr.h>
#include <intrinsics.h>
#include "keyboard.h"

//отправка символа по usart`у
void USART_SendChar(unsigned char sym)
{
  while(!(UCSRA & (1<<UDRE)));
  UDR = sym;
}

//прерывание таймера Т0 - опрос клавиатуры
#pragma vector = TIMER0_OVF_vect
__interrupt void Timer0Ovf(void)
{
   TCNT0 = 0x83;
   ScanKeyboard();   
}

int main( void )
{
  unsigned char key;
  
  //инициализация USART`a
  UBRRH = 0;
  UBRRL = 51; //скорость обмена 9600 бод при Fcpu = 8MГц
  UCSRB = (1<<TXEN); // разр передачи.
  UCSRC = (1<<URSEL)|(1<<UCSZ1)|(1<<UCSZ0); //размер слова 8 разрядов
  
  //инициализация таймера Т0
  TIMSK = (1<<TOIE0); //разрешение прерывания по переполнению
  TCCR0 = (1<<CS02)|(0<<CS01)|(0<<CS00); //предделитель 256
  TCNT0 = 0x83; //прерывания каждые 4 мс
  
  //инициализация портов и переменных 
  InitKeyboard();
  __enable_interrupt();
  
  while(1){
    //если зафиксировано нажатие, 
    //отправить код кнопки в терминал
    key = GetKey();
    if (key)
      USART_SendChar(key);
  }
  return 0;
}


#include <ioavr.h>
#include <intrinsics.h>

int main( void )
{
  
  PORTA = 0x00; //выключаем порт
  DDRA = 0xFF;  //устанавлеваем работу порта на выход
  
  PORTB = 0x00;
  DDRB = 0xFF;
  
  PORTC = 0x00;
  DDRC = 0xFF;

  PORTD = 0x00;
  DDRD = 0xFF;
  
  while(1){
    //поочередное включение каждого выхода кажного из портов последовательно
    for(int i = 0; i < 8; i++){
      PORTA = 1<<i;
      __delay_cycles(800000);      
    }
    PORTA = 0<<7;
    for(int i = 0; i < 8; i++){
      PORTB = 1<<i;
      __delay_cycles(800000);      
    }
    PORTB = 0<<7;
    for(int i = 0; i < 8; i++){
      PORTC = 1<<i;
      __delay_cycles(800000);      
    }
    PORTC = 0<<7;
    for(int i = 0; i < 8; i++){
      PORTD = 1<<i;
      __delay_cycles(800000);      
    }
    PORTD = 0<<7;
    // поочередное включение всех выводов всех портов
    for(int i = 0; i < 8; i++){
      PORTA |= 1<<i;
      __delay_cycles(800000);      
    }
    for(int i = 0; i < 8; i++){
      PORTB |= 1<<i;
      __delay_cycles(800000);      
    }
    for(int i = 0; i < 8; i++){
      PORTC |= 1<<i;
      __delay_cycles(800000);      
    }
    for(int i = 0; i < 8; i++){
      PORTD |= 1<<i;
      __delay_cycles(800000);      
    }
    PORTA = PORTB = PORTC = PORTD = 0x00;    
  }
  return 0;
}

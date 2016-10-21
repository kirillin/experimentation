#include <ioavr.h>
#include <intrinsics.h>


int main( void )
{
  
  PORTA = 0x00;
  DDRA = 0x00;
  
  PORTB = 0x00;
  DDRB = 0xFF;
  
  PORTC = 0x00;
  DDRC = 0xFF;

  PORTD = 0x00;
  DDRD = 0xFF;
 
  
  
  while(1){
 
    if( (PINA & (1<<0)) ){
      PORTB = 1<<0;
    }
    if((PINA & (1<<1))) PORTB = PORTC = PORTD = 0xff;
    
  }
  return 0;
}

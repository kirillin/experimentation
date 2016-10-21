//*****************************************************************************
//
//  Author(s)...: Pashgan    http://ChipEnable.Ru   
//
//  Target(s)...: �����
//
//  Compiler....: �����
//
//  Description.: ������������� ������� ��������� ����������
//
//  Data........: 12.10.2011
//
//******************************************************************************
#include "keyboard.h"


//������ ��� ������������� � �������������**************************************
//������ ����� ������ �� ����

#ifdef  __ICCAVR__
  #include <ioavr.h>
  #include <intrinsics.h>
  
  #define _delay_us(value)  __delay_cycles((F_CPU / 1000000) * (value));
  #define FLASH_ATR         __flash
  #define read_flash(adr)   adr
#endif

#ifdef  __GNUC__
  #include <avr/io.h>
  #include <util/delay.h>
  #include <avr/interrupt.h>
  #include <avr/pgmspace.h>  
  
  #define FLASH_ATR         PROGMEM
  #define read_flash(adr)   pgm_read_byte(&(adr))
#endif

#ifdef __CODEVISIONAVR__
  #include <io.h>
  #include <delay.h>
  
  #define _delay_us(value) delay_us(value)
  #define FLASH_ATR         __flash
  #define read_flash(adr)   adr
#endif
//******************************************************************************




#ifdef KEYBOARD_4X4  
   #warning "Keyboard 4x4"

   #define KEYS 16
   #define SCAN_CYCLES 4
   #define ROW_MASK ((1<<PIN_ROW1)|(1<<PIN_ROW2)|(1<<PIN_ROW3)|(1<<PIN_ROW4))
   #define COL_MASK ((1<<PIN_COL1)|(1<<PIN_COL2)|(1<<PIN_COL3)|(1<<PIN_COL4))
  
  //������� �������������
  FLASH_ATR unsigned char keyTable[][2] = { 
  {((~(1<<PIN_ROW1)&(ROW_MASK))|(~(1<<PIN_COL1)&(COL_MASK))), EVENT_KEY1},
  {((~(1<<PIN_ROW1)&(ROW_MASK))|(~(1<<PIN_COL2)&(COL_MASK))), EVENT_KEY2},
  {((~(1<<PIN_ROW1)&(ROW_MASK))|(~(1<<PIN_COL3)&(COL_MASK))), EVENT_KEY3},
  {((~(1<<PIN_ROW1)&(ROW_MASK))|(~(1<<PIN_COL4)&(COL_MASK))), EVENT_KEYA},

  {((~(1<<PIN_ROW2)&(ROW_MASK))|(~(1<<PIN_COL1)&(COL_MASK))), EVENT_KEY4},
  {((~(1<<PIN_ROW2)&(ROW_MASK))|(~(1<<PIN_COL2)&(COL_MASK))), EVENT_KEY5},
  {((~(1<<PIN_ROW2)&(ROW_MASK))|(~(1<<PIN_COL3)&(COL_MASK))), EVENT_KEY6}, 
  {((~(1<<PIN_ROW2)&(ROW_MASK))|(~(1<<PIN_COL4)&(COL_MASK))), EVENT_KEYB},  
 
  {((~(1<<PIN_ROW3)&(ROW_MASK))|(~(1<<PIN_COL1)&(COL_MASK))), EVENT_KEY7},
  {((~(1<<PIN_ROW3)&(ROW_MASK))|(~(1<<PIN_COL2)&(COL_MASK))), EVENT_KEY8},
  {((~(1<<PIN_ROW3)&(ROW_MASK))|(~(1<<PIN_COL3)&(COL_MASK))), EVENT_KEY9},    
  {((~(1<<PIN_ROW3)&(ROW_MASK))|(~(1<<PIN_COL4)&(COL_MASK))), EVENT_KEYC}, 

  {((~(1<<PIN_ROW4)&(ROW_MASK))|(~(1<<PIN_COL1)&(COL_MASK))), EVENT_KEYZ},
  {((~(1<<PIN_ROW4)&(ROW_MASK))|(~(1<<PIN_COL2)&(COL_MASK))), EVENT_KEY0},
  {((~(1<<PIN_ROW4)&(ROW_MASK))|(~(1<<PIN_COL3)&(COL_MASK))), EVENT_KEYR}, 
  {((~(1<<PIN_ROW4)&(ROW_MASK))|(~(1<<PIN_COL4)&(COL_MASK))), EVENT_KEYD} 
  };

#else  
  #warning "Keyboard3x4"

  #define KEYS 12
  #define SCAN_CYCLES 3
  #define ROW_MASK ((1<<PIN_ROW1)|(1<<PIN_ROW2)|(1<<PIN_ROW3)|(1<<PIN_ROW4))
  #define COL_MASK ((1<<PIN_COL1)|(1<<PIN_COL2)|(1<<PIN_COL3))
  
  //������� �������������
  FLASH_ATR unsigned char keyTable[][2] = { 
  {((~(1<<PIN_ROW1)&(ROW_MASK))|(~(1<<PIN_COL1)&(COL_MASK))), EVENT_KEY1},
  {((~(1<<PIN_ROW1)&(ROW_MASK))|(~(1<<PIN_COL2)&(COL_MASK))), EVENT_KEY2},
  {((~(1<<PIN_ROW1)&(ROW_MASK))|(~(1<<PIN_COL3)&(COL_MASK))), EVENT_KEY3},
    
  {((~(1<<PIN_ROW2)&(ROW_MASK))|(~(1<<PIN_COL1)&(COL_MASK))), EVENT_KEY4},
  {((~(1<<PIN_ROW2)&(ROW_MASK))|(~(1<<PIN_COL2)&(COL_MASK))), EVENT_KEY5},
  {((~(1<<PIN_ROW2)&(ROW_MASK))|(~(1<<PIN_COL3)&(COL_MASK))), EVENT_KEY6}, 
    
  {((~(1<<PIN_ROW3)&(ROW_MASK))|(~(1<<PIN_COL1)&(COL_MASK))), EVENT_KEY7},
  {((~(1<<PIN_ROW3)&(ROW_MASK))|(~(1<<PIN_COL2)&(COL_MASK))), EVENT_KEY8},
  {((~(1<<PIN_ROW3)&(ROW_MASK))|(~(1<<PIN_COL3)&(COL_MASK))), EVENT_KEY9},    

  {((~(1<<PIN_ROW4)&(ROW_MASK))|(~(1<<PIN_COL1)&(COL_MASK))), EVENT_KEYZ},
  {((~(1<<PIN_ROW4)&(ROW_MASK))|(~(1<<PIN_COL2)&(COL_MASK))), EVENT_KEY0},
  {((~(1<<PIN_ROW4)&(ROW_MASK))|(~(1<<PIN_COL3)&(COL_MASK))), EVENT_KEYR}
  };

#endif

#define DELAY 5
  
//��������� ��������
unsigned char keyState = 0;
//��� ������
unsigned char keyCode;
//���������� ��� ������
volatile unsigned char keyValue;
//���� - ������ ������ � ������������
unsigned char keyDown;
//���� - ������ ����� ������
volatile unsigned char keyNew;

unsigned char KEYB_AnyKey(void);
unsigned char KEYB_SameKey(void);
void KEYB_ScanKey(void);
void KEYB_FindKey(void);
void KEYB_ClearKey(void);

//������������� ������, 
//��������� ����������
void KEYB_Init(void)
{
#ifndef COMMON_BUS  
  //����, ��������
  DDRX_ROW &= ~ROW_MASK;
  PORTX_ROW |= ROW_MASK;
  //�����, ����
  DDRX_COL |= COL_MASK; 
  PORTX_COL &= ~COL_MASK;
#endif //COMMON_BUS   
  
  keyState = 0;
  keyCode = 0;
  keyValue = 0;
  keyDown = 0;
  keyNew = 0;
}


void KEYB_ScanKeyboard(void)
{
#ifdef COMMON_BUS
  unsigned char buf[4];
  buf[0] = DDRX_COL;
  buf[1] = PORTX_COL;
  buf[2] = DDRX_ROW;
  buf[3] = PORTX_ROW;
  
  //����, ��������
  DDRX_ROW &= ~ROW_MASK;
  PORTX_ROW |= ROW_MASK;
  
  //�����, ����
  DDRX_COL |= COL_MASK; 
  PORTX_COL &= ~COL_MASK;
#endif //COMMON_BUS  
  
  switch (keyState){
     case 0: {
       if (KEYB_AnyKey()) {
         KEYB_ScanKey();
         keyState = 1;
       }
     }
     break;
     
     case 1: {
       if (KEYB_SameKey()) {
         KEYB_FindKey();
         keyState = 2;   
       }
       else keyState = 0;
     }
     break;
     
     case 2: {
       if (KEYB_SameKey()) {}
       else keyState = 3;
     }
     break;
     
     case 3: {
       if (KEYB_SameKey()) {
         keyState = 2;
       }
       else {
         KEYB_ClearKey();
         keyState = 0;
       }
     }
     break;
     
     default:
       break;
   }
  
#ifdef COMMON_BUS  
  DDRX_COL = buf[0];
  PORTX_COL = buf[1];
  DDRX_ROW = buf[2];    
  PORTX_ROW = buf[3];
#endif //COMMON_BUS  
}


//���������� true ���� ����� ������ ������ ������
unsigned char KEYB_AnyKey(void) 
{  
  PORTX_COL &= ~COL_MASK;
  _delay_us(DELAY);
  return (~PINX_ROW & ROW_MASK);
}

//���������� true, ���� ������������ �� �� ������,
//��� � � ���������� ����� ������
unsigned char KEYB_SameKey(void) 
{
  PORTX_COL &= ~COL_MASK;
  PORTX_COL |= (keyCode & COL_MASK);
  return ((~keyCode & ROW_MASK)&(~PINX_ROW));
}


//��������� ����������
void KEYB_ScanKey(void) 
{
    unsigned char i;
    for(i = 0; i<SCAN_CYCLES; i++){
       PORTX_COL |= COL_MASK;
       if (i == 0) PORTX_COL &= ~(1<<PIN_COL1);
       if (i == 1) PORTX_COL &= ~(1<<PIN_COL2);
       if (i == 2) PORTX_COL &= ~(1<<PIN_COL3);
       #ifdef KEYBOARD_4X4
       if (i == 3) PORTX_COL &= ~(1<<PIN_COL4);
       #endif
       _delay_us(DELAY);
       if (~PINX_ROW & ROW_MASK) {
         keyCode = PINX_ROW & ROW_MASK;
         keyCode |= PORTX_COL & COL_MASK;
         return;
       }
    }
}



//���� ���������� ��� ������ �� �������
//��������� �������� ������
void KEYB_FindKey(void) 
{
  unsigned char index;
  for (index = 0; index < KEYS; index++) {
    if (read_flash(keyTable [index][0]) == keyCode) {
      keyValue = read_flash(keyTable [index][1]);
      keyDown = 1;
      keyNew = 1;
      return;
    }
  }
}

//���������� ���� 
void KEYB_ClearKey(void) 
{
  keyDown = 0;
}

//���������� ��� ������� ������
unsigned char KEYB_GetKey(void)
{
  if (keyNew){
    keyNew = 0;
    return keyValue;
  }
  else 
    return EVENT_NULL;
}


unit Unit1;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs, Vcl.StdCtrls;

type
  TForm1 = class(TForm)
    Button1: TButton;
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Edit1: TEdit;
    Label4: TLabel;
    procedure Button1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form1: TForm1;

implementation

{$R *.dfm}

function decToBin(dec: longint): string;
const
	digits: array [0..1] of char = ('0','1');
var
	res: string;
	d: 0..1;
begin
	result := '';
  while (dec <> 0) do
	begin
  	d := dec mod 2;
		res := digits[d] + res;
    dec := dec div 2;
  end;
  decToBin := res;
end;

function binToDec(bin: string): longint;
const
	digits: array [0..1] of char = ('0','1');
var
	res, rank: longint;
	i, j :byte;
begin
  res := 0;
	rank := 1;
  for i := length(bin) downto 1 do
	begin
  	j := 0;
		while (digits[j] <> bin[i]) do inc(j);
    res := res + rank * j;
    rank := rank * 2;
   end;
   binToDec:=res;
end;

function nod(x,y:longint):longint; { фукнция поиска наиб. общ. делителя }
begin
   if x<>0 then NOD:=NOD(y mod x,x) else NOD:=y;
end;

function rightShift(binaryString: string; rotdist:integer): string;
var
	stringlength: integer;
	qtyIteration: integer;
	i, j, k: integer;
	temp: char;
begin
	stringLength := length(binaryString);
	qtyIteration := nod(stringLength, rotdist);
	for i := qtyIteration downto 1 do
	begin
		temp := binaryString[i];
		j := i;
		while (j > 0) do
		begin
			k := j - rotdist;
			if (k < 1) then k := k + stringLength;
			if (k = i) then break;
			binaryString[j] := binaryString[k];
			j := k;
		end;
		binaryString[j] := temp;
	end;
	rightShift := binaryString;
end;

procedure TForm1.Button1Click(Sender: TObject);
var
	n, i: longint;
	binOfMaxNumber, tempBin, binaryN: string;
	tempNumber, maxNumber: longint;
  number: string;
begin
  number := Form1.Edit1.Text;
  if ((number <> '')) then
  begin
    if (length(number) <= 10) then
    begin
      n := strToInt(number);
      binaryN := decToBin(n);
      maxNumber := n;
      binOfMaxNumber := binaryN;
      for i := 1 to length(binaryN) do
      begin
        binaryN := rightShift(binaryN, 1);
        tempNumber := binToDec(binaryN);
        if (tempNumber > maxNumber) then
        begin
          maxNumber := tempNumber;
          binOfMaxNumber := binaryN;
        end;
      end;
      Form1.Label3.Caption :=
                intToStr(maxNumber) + ' (' + binOfMaxNumber + ')';
    end else ShowMessage('Вы ввели слишком большое число!');
  end else ShowMessage('Введите в поле число!');

end;

end.

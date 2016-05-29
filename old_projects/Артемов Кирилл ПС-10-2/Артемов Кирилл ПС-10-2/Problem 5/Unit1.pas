{
  The author is Artemov Kirill
  Problem 5. Decoding
  KSTU INTER 2014
}

unit Unit1;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs, Vcl.Grids, Vcl.StdCtrls, Vcl.ExtCtrls;

type
  TForm1 = class(TForm)
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    StringGrid1: TStringGrid;
    Edit3: TEdit;
    Label5: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    Memo1: TMemo;
    Memo2: TMemo;
    Memo3: TMemo;
    Button1: TButton;
    Label8: TLabel;
    Button2: TButton;
    Button3: TButton;
    procedure Edit3Change(Sender: TObject);
    procedure StringGrid1Click(Sender: TObject);
    procedure Button1Click(Sender: TObject);
    procedure Button2Click(Sender: TObject);
    procedure Button3Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

const
  SIZE = 20;

var
  Form1: TForm1;
  n, qtyHoles: longint;
	encryptData, mask, temp:	array[1..20,1..20] of string;

implementation

{$R *.dfm}
{
  copying grid from StringGrid component to array of strings
}
procedure initMask();
var
  i,j: longint;
begin
  for i := 0 to n-1 do
    for j := 0 to n-1 do
      mask[i+1,j+1] := Form1.StringGrid1.Cells[j,i];
end;

{
  Fill encryptData array by symbol '$'
}
procedure initEncryptData();
var
  i,j: longint;
begin
  for i := 1 to n do
    for j := 1 to n do
      encryptData[i,j] := '$';
end;

{
  rotate mask array
    if direction = 1 then rotate ckw
    if direction = -1 then rotate counterclockwise
}
procedure rotate(direction: integer);
var
	i,j: longint;
begin
	temp := mask;
		for i := 1 to n do
			for j := 1 to n do
				if (direction = 1) then
					mask[j, n - i +1] := temp[i,j]
				else if (direction = -1) then
					mask[n-j+1, i] := temp[i,j];
end;

{
 encrypt txt according to the rules of problem 5
}
function encrypt(txt: string):string;
var
	k,i,j,pos: longint;
	res: string;
begin
	pos := 1;
  if (length(txt) < n*n) then
  for i := length(txt)+1 to n*n do
    txt := txt + ' ';
	for k := 1 to 4 do
	begin
		for i := 1 to n do
			for j := 1 to n do
			begin
				if (mask[i,j] = 'O') then
				begin
					encryptData[i,j] := txt[pos];
					inc(pos);
				end;
			end;
		rotate(1);
	end;
	res := '';
	for i := 1 to n do
		for j := 1 to n do
			res := res + encryptData[i,j];
	encrypt := res;
end;

{
 decoding txt according to the rules of problem 5
}
function decoding(txt: string): string;
var
	pos, k, i, j: longint;
	res: string;
begin
	pos := 1;
	res := '';
	for i := 1 to n do
		for j := 1 to n do
		begin
      if (pos <= length(txt)) then
      begin
  			encryptData[i,j] := txt[pos];
	  		inc(pos);
      end;
		end;
	pos := 1;
	for k := 1 to 4 do
	begin
		rotate(-1);
		for i := n downto 1 do
			for j := n downto 1 do
			begin
				if (mask[i,j] = 'O') then
        begin
					res := encryptData[i,j] + res;
          inc(pos);
        end;
			end;
	end;
	decoding := res;
end;

{
  reads text from memo
}
function readFromMemo(memo:TMemo):string;
var
  i, len: longint;
  txt: string;
begin
  txt := '';
  len := length(memo.Text);
  for I := 1 to len do
    txt := txt + memo.Text[i];
  readFromMemo := txt;
end;

procedure TForm1.Button1Click(Sender: TObject);
var
  txt, encryptTxt: string;
begin
  if (Memo1.Text <> '') then
  begin
    initEncryptData();
    initMask();
    txt := readFromMemo(Memo1);
    encryptTxt := encrypt(txt);
    Form1.Memo2.Text := encryptTxt;
  end else ShowMessage('Ââåäèòå ñòðîêó');
end;

procedure TForm1.Button2Click(Sender: TObject);
var
  txt, decodedTxt: string;
begin
  if (Memo2.Text <> '') then
  begin
    initEncryptData();
    initMask();
    txt := readFromMemo(Memo2);
    decodedTxt := decoding(txt);
    Form1.Memo3.Text := decodedTxt;
    Form1.Label7.Visible := true;
    Form1.Memo3.Visible := true;
  end else ShowMessage('Ââåäèòå ñòðîêó');
end;

procedure TForm1.Button3Click(Sender: TObject);
var
i,j: Integer;
begin
  for i:=0 to Form1.StringGrid1.ColCount-1 do
     Form1.StringGrid1.Cols[i].Clear;
end;

{
  Creats table according to the entered N
}
procedure TForm1.Edit3Change(Sender: TObject);
var
  ñellSize,i,j: Integer;
begin
  n := strToInt(Form1.Edit3.Text);
  for i:=0 to Form1.StringGrid1.ColCount-1 do
     Form1.StringGrid1.Cols[i].Clear;
  ñellSize := 20;
  Form1.StringGrid1.ColCount := n;
  Form1.StringGrid1.RowCount := n;
  for i := 0 to n-1 do
    for j := 0 to n-1 do
    begin
      Form1.StringGrid1.ColWidths[i] := ñellSize;
      Form1.StringGrid1.RowHeights[i] := ñellSize;
    end;
  Form1.StringGrid1.Width := ñellSize * n +2*n+n;
  Form1.StringGrid1.Height := ñellSize * n +2*n+n;
  Form1.StringGrid1.Visible := true;
  Form1.Label5.Visible := true;
  Form1.Button3.Visible := true;
end;

{
  When pressed to one of cells it will be sored as 'O'
  And other cells(which are calculated by rotation the matrix)
        will be sored as 'X'
}
procedure TForm1.StringGrid1Click(Sender: TObject);
var
  x,y,i,j: longint;
begin
  qtyHoles := 0;
  Form1.Memo1.Clear;
  Form1.Memo2.Clear;
  Form1.Memo3.Clear;
  x := Form1.StringGrid1.Row;
  y := Form1.StringGrid1.Col;
  Form1.StringGrid1.Cells[y,x] := 'O';
  Form1.StringGrid1.Cells[n-x-1,y] := 'X';
  Form1.StringGrid1.Cells[n-y-1,n-x-1] := 'X';
  Form1.StringGrid1.Cells[x,n-y-1] := 'X';
  for i := 0 to n-1 do
    for j := 0 to n-1 do
      if (Form1.StringGrid1.Cells[j,i] = 'O') then
        qtyHoles := qtyHoles + 1;
  Form1.Memo1.MaxLength := qtyHoles*4;
  Form1.Label6.Caption := intToStr(qtyHoles*4);
end;

end.

import React, { ReactNode, ChangeEvent, MouseEvent } from 'react'
import { TextField } from '@mui/material'

export interface ITextFieldProps {
  variant: 'outlined' | 'standard' | 'filled'
  placeholder: string
  type: 'text'
  sx?: object
  value?: string
  onClick?: (event: MouseEvent<HTMLInputElement>) => void
  onChange?: (event: ChangeEvent<HTMLInputElement>) => void
}
const Input = ({ variant, placeholder, type, sx, value, onChange, onClick }: ITextFieldProps) => {
  return (
    <TextField
      variant={variant}
      placeholder={placeholder}
      type={type}
      sx={sx}
      value={value}
      onClick={onClick}
      onChange={onChange}
    />
  )
}

export default Input
